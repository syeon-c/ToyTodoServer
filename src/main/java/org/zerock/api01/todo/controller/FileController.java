package org.zerock.api01.todo.controller;

import io.minio.GetObjectResponse;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.common.util.service.MinioService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("api/files/")
@RequiredArgsConstructor
public class FileController {
//    private final static String imageDirectory = Paths.get("").toAbsolutePath() + "/images/";
//
//    public FileController() {
//        File file = new File(imageDirectory);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//    }

    private final MinioService minioService;

    private String getExtension(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        int index = fileName.indexOf(".");
        if (index > -1) {
            return fileName.substring(index);
        }
        return "";
    }

//    @PostMapping("")
//    public List<String> upload(@RequestPart List<MultipartFile> files) throws Exception {
//
//        List<String> list = new ArrayList<>();
//
//        log.info("==========FileControl============");
//
//
//        for (MultipartFile file : files) {
//            String originalFilename = file.getOriginalFilename();
//            list.add(originalFilename);
//        }
//
//        log.info("imgInfo: " + list);
//
//        return list;
//    }

    @PostMapping("upload")
    public List<String> uploadFiles(@RequestParam("files") List<MultipartFile> files) {

        List<String> fileNames = new ArrayList<>();

        log.info("===========File Upload===========");
        log.info(files);

        for (MultipartFile multipartFile : files) {

            fileNames.add(minioService.uploadFile(multipartFile));

//            String filePath = imageDirectory + UUID.randomUUID() + getExtension(multipartFile);

//            try (FileOutputStream writer = new FileOutputStream(filePath)) {
//                writer.write(multipartFile.getBytes());
//
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//                throw new RuntimeException("Fail to upload files.");
//            }
        }
        return fileNames;
    }

    @GetMapping("{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        log.info(fileName);

        GetObjectResponse getObjectResponse = minioService.getFileObjectResponse(fileName);
        byte[] fileByte = minioService.getFileByteArr(getObjectResponse);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(getObjectResponse.headers().get("Content-Type")));

        log.info("=================================");
        log.info("Byte: " + fileByte);
        log.info("Header: " + headers);

        return new ResponseEntity<>(fileByte, headers, HttpStatus.OK);

    }


}
