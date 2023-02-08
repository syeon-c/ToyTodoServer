package org.zerock.api01.todo.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.api01.todo.dto.FileAddDTO;
import org.zerock.api01.todo.dto.FileInfoDTO;
import org.zerock.api01.todo.dto.TodoDTO;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class FileMapperTest {

    @Autowired(required = false)
    FileMapper fileMapper;

    @Autowired(required = false)
    private TodoMapper todoMapper;


    @Test
    public void testSoftDeleteFiles() {

        List<Long> fnos = new ArrayList<>();
        fnos.add(12L);
        fnos.add(13L);

        fileMapper.softDeleteFiles(fnos);

    }

    @Test
    public void testModifiedAddFiles() {

        Long tno = 7L;
        FileInfoDTO fileInfoDTO = FileInfoDTO.builder()
                .fname("Files1")
                .isMain(true)
                .build();

        FileAddDTO fileAddDTO = FileAddDTO.builder()
                .tno(tno)
                .fileInfo(List.of(fileInfoDTO))
                .build();

        fileMapper.addFiles(fileAddDTO);

    }

    @Test
    public void testSetMainFalse() {

        Long tno = 7L;

        fileMapper.setMainFalse(tno);

    }

    @Test
    public void testUpdateMain() {

        Long fno = 10L;

        fileMapper.updateMain(fno);

    }
}
