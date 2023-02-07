package org.zerock.api01.common.util.service;

import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.todo.service.FileService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

@Component
@Transactional
@Log4j2
@RequiredArgsConstructor
public class SchedulerService {

    private final FileService fileService;

    private final MinioService minioService;

    @Async
    @Scheduled(cron = "*/30 * * * * ?")
    public void test() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        log.info("cron test");
    }


    @Async
    @Scheduled(cron = "*/30 * * * * ?")
    public void cronScheduler() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<String> fileList = fileService.getDeletedFiles(LocalDate.now());
        minioService.deleteFile(fileList);
        log.info(fileList);
    }
}
