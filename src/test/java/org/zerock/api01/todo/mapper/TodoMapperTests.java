package org.zerock.api01.todo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.api01.common.dto.PageRequestDTO;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.dto.TodoRequestDTO;

import java.util.List;
import java.util.stream.IntStream;


@SpringBootTest
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

        IntStream.rangeClosed(1, 500).forEach((i) -> {
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

        List<TodoDTO> dtoList = todoMapper.getList(todoRequestDTO);
        System.out.println("Search: " + dtoList);
    }

    @Test
    public void testList() {

        TodoRequestDTO todoRequestDTO = new TodoRequestDTO();
        todoRequestDTO.setPage(1);
        todoRequestDTO.setSize(10);
        todoRequestDTO.setCondition("title");
        todoRequestDTO.setKeyword(null);
        todoMapper.getCount(todoRequestDTO);

        //sSystem.out.println("Search: " + dtoList);
    }
}
