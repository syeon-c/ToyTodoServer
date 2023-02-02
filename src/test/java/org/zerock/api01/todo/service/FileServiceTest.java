package org.zerock.api01.todo.service;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.api01.todo.dto.FileDTO;

@SpringBootTest
@Log4j2
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    public void addTest() {

        FileDTO fileDTO = FileDTO.builder()
                .fname("test")
                .tno(2L)
                .build();

        fileService.addFiles(fileDTO);
    }
}
