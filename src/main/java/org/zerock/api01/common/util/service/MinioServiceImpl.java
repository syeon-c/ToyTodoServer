package org.zerock.api01.common.util.service;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.common.dto.MinioDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    private String setUUID(String name) {
        return LocalDateTime.now() + name;
    }

    public List<Bucket> connectMinio() {
        try{
            return minioClient.listBuckets();
        } catch (Exception e){
            log.info(e);
            return null;
        }
    }

    private String getPreSignedUrl(String filename) {
        return "http://localhost:8081/file/".concat(filename);
    }

    @Override
    public List<MinioDTO> getListObjects() {
        List<MinioDTO> objects = new ArrayList<>();
        try {
            Iterable<Result<Item>> result = minioClient.listObjects(ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .recursive(true)
                    .build());
            for (Result<Item> item : result) {
                objects.add(MinioDTO.builder()
                        .filename(item.get().objectName())
                        .size(item.get().size())
                        .url(getPreSignedUrl(item.get().objectName()))
                        .build());
            }
            return objects;
        } catch (Exception e) {
            log.error("Happened error when get list objects from minio: ", e);
        }

        return objects;
    }

    @Override
    public GetObjectResponse getFileObjectResponse(String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        GetObjectResponse getObjectResponse =  minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build());

        return getObjectResponse;
    }

    @Override
    public byte[] getFileByteArr(GetObjectResponse response) throws IOException {

        return IOUtils.toByteArray(response);

    }

    public String uploadFile(MultipartFile multipartFile) {
        String fileName = "";

        try {
            log.info("File Uploaded...." + multipartFile.getName() + ", " + multipartFile.getContentType());

            fileName = setUUID(multipartFile.getOriginalFilename());
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .contentType(multipartFile.getContentType())
                    .object(fileName)
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .build());

        } catch (Exception e) {
            log.error("Happened error when upload file: ", e);
        }
//        return FileDTO.builder()
//                .title(request.getTitle())
//                .description(request.getDescription())
//                .size(request.getFile().getSize())
//                .url(getPreSignedUrl(request.getFile().getOriginalFilename()))
//                .filename(request.getFile().getOriginalFilename())
//                .build();
        return fileName;
    }

    @Override
    public void saveThumbnail(MultipartFile file, String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        try {
            InputStream thumbNailInputStream = getThumbNailInputStream(file);
            String thumbNailPrefix = "t_";

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(thumbNailPrefix + fileName)
                    .contentType(file.getContentType())
                    .stream(thumbNailInputStream, thumbNailInputStream.available(), -1)
                    .build());
        } catch (Exception e) {
            log.error("Happened error when upload file: ", e);
            throw e;
        }
    }

    private InputStream getThumbNailInputStream(MultipartFile file) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try{
            Thumbnailator.createThumbnail(file.getInputStream(),bos,200,200);
            InputStream is = new ByteArrayInputStream(bos.toByteArray());
            return is;
        } catch (UnsupportedFormatException exception){
            throw exception;
        } catch (Exception e){
            throw e;
        } finally {
            bos.close();
        }
    }

    @Override
    public void deleteFile(String fileName) {

        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build());

            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object("t_" + fileName)
                    .build());
        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }
    @Override
    public void deleteFile(List<String> filenames) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        try {
            List<DeleteObject> objects = new LinkedList<>();
            String thumbNailPrefix = "t_";

            for(String filename: filenames){
                objects.add(new DeleteObject(filename));
                objects.add(new DeleteObject(thumbNailPrefix + filename));
            }
            Iterable<Result<DeleteError>> results = minioClient.removeObjects(RemoveObjectsArgs.builder()
                    .bucket(bucketName)
                    .objects(objects)
                    .build());

            log.info("==========================================");
            for(Result<DeleteError> result : results){
                DeleteError error = result.get();
                log.info(error.objectName() + "\n" + error.message());
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
