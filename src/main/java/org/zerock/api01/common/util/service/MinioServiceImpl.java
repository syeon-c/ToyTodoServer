package org.zerock.api01.common.util.service;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.common.dto.MinioDTO;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
}
