package org.zerock.api01.todo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequestMapping("api/files/")
public class FileController {

    private final static String imageDirectory = Paths.get("").toAbsolutePath() + "/images/";

    public FileController() {
        File file = new File(imageDirectory);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

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
    public void uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        log.info("===========File Upload===========");

        log.info(files);

        for (MultipartFile multipartFile : files) {
            String filePath = imageDirectory + UUID.randomUUID() + getExtension(multipartFile);

            try (FileOutputStream writer = new FileOutputStream(filePath)) {
                writer.write(multipartFile.getBytes());

            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException("Fail to upload files.");
            }
        }
    }
}
