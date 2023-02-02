package org.zerock.api01.minio;

import io.minio.GetObjectResponse;
import io.minio.errors.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.zerock.api01.common.util.service.MinioServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
@Log4j2
public class MinioTest {

    @Autowired
    MinioServiceImpl minioService;

    @Test
    public void testMinIO() {

        log.info(minioService.connectMinio().get(0).name());

    }

    @Test
    public void updateImageTest() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "test",
                "test.png",
                "image/png",
                new FileInputStream("/Users/choeseoyeon/Desktop/학부모이미지.jpeg")
        );

//        FileDTO fileDTO = FileDTO.builder()
//                .title("testFile")
//                .description("test file")
//                .file(mockMultipartFile)
//                .build();

        log.info(minioService.uploadFile(mockMultipartFile));
    }

    @Test
    public void getImageTestByByteArr() throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        String fileName = "2023-02-02T15:56:42.871324test.png";
        GetObjectResponse response = minioService.getFileObjectResponse(fileName);
        byte[] arr = minioService.getFileByteArr(response);

        log.info("============Get Image Test=============");
        log.info(arr);

    }

}
