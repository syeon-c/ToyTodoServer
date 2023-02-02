package org.zerock.api01.common.util.service;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.common.dto.FileDTO;

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
    public List<FileDTO> getListObjects() {
        List<FileDTO> objects = new ArrayList<>();
        try {
            Iterable<Result<Item>> result = minioClient.listObjects(ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .recursive(true)
                    .build());
            for (Result<Item> item : result) {
                objects.add(FileDTO.builder()
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
    public FileDTO uploadFile(FileDTO request) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(request.getFile().getOriginalFilename())
                    .stream(request.getFile().getInputStream(), request.getFile().getSize(), -1)
                    .build());
        } catch (Exception e) {
            log.error("Happened error when upload file: ", e);
        }
        return FileDTO.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .size(request.getFile().getSize())
                .url(getPreSignedUrl(request.getFile().getOriginalFilename()))
                .filename(request.getFile().getOriginalFilename())
                .build();
    }
}
