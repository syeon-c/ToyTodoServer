package org.zerock.api01.todo.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.api01.common.dto.PageRequestDTO;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.dto.TodoListDTO;
import org.zerock.api01.todo.dto.TodoRequestDTO;

import java.util.List;
import java.util.stream.IntStream;


@SpringBootTest
@Log4j2
public class TodoMapperTests {

    @Autowired(required = false)
    TodoMapper todoMapper;

    @Test
    public void getTodo() {

        System.out.println("Test: " + todoMapper.getTodo(1L));
    }

    @Test
    public void deleteTodo() {

        todoMapper.deleteTodo(2L);
    }

//    @Test
//    public void testList() {
//
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
//
//        List<TodoDTO> dtoList = todoMapper.getList(pageRequestDTO);
//
//        int count = todoMapper.getCount(pageRequestDTO);
//
//        System.out.println("COUNT: " + count);
//    }

    @Test
    public void testAddList() {

        IntStream.rangeClosed(1, 100).forEach((i) -> {
            TodoDTO todo = TodoDTO.builder()
                    .title("Title" + i)
                    .writer("user" + i)
                    .build();

            todoMapper.addTodo(todo);
        });

    }

    @Test
    public void testSearchList() {

        TodoRequestDTO todoRequestDTO = new TodoRequestDTO();
        todoRequestDTO.setPage(1);
        todoRequestDTO.setSize(10);
        todoRequestDTO.setCondition("total,writer");
        todoRequestDTO.setKeyword("11");

        List<TodoListDTO> dtoList = todoMapper.getList(todoRequestDTO);
        System.out.println("Search: " + dtoList);
    }

    @Test
    public void testList() {

        TodoRequestDTO todoRequestDTO = new TodoRequestDTO();
        todoRequestDTO.setPage(1);
        todoRequestDTO.setSize(10);
        todoRequestDTO.setCondition("title");
        todoRequestDTO.setKeyword("11");
        todoMapper.getCount(todoRequestDTO);
    }

    @Test
    public void getTodoTest() {

        todoMapper.getTodo(131L);

    }

    @Test
    public void getThumbnailTest() {

        TodoRequestDTO todoRequestDTO = new TodoRequestDTO();
        todoRequestDTO.setPage(1);
        todoRequestDTO.setSize(10);
        todoRequestDTO.setCondition("title");
        todoRequestDTO.setKeyword("cat");


        List<TodoListDTO> dtoList = todoMapper.getList(todoRequestDTO);
        log.info("Thumbnail......." + dtoList);

    }
}
