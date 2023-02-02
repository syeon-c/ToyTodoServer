package org.zerock.api01.todo.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.api01.todo.dto.FileDTO;
import org.zerock.api01.todo.dto.TodoDTO;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class FileMapperTest {

    @Autowired(required = false)
    FileMapper fileMapper;

    @Autowired
    private TodoMapper todoMapper;

    @Test
    public void testAddList() {

       TodoDTO todoDTO = TodoDTO.builder()
               .title("ttt")
               .writer("user")
               .build();

       todoMapper.addTodo(todoDTO);

        FileDTO fileDTO = FileDTO.builder()
                .fname("ccc")
                .build();

        fileMapper.addFiles(fileDTO);

    }

}
