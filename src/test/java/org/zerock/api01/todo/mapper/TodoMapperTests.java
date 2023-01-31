package org.zerock.api01.todo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.api01.todo.dto.TodoDTO;

import java.util.List;

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

    @Test
    public void getTodoList() {
        List<TodoDTO> lst = todoMapper.getList();
        for(int i=0; i<lst.size(); ++i){
            System.out.println(lst.get(i).getTitle());
        }
        System.out.println("getTodoList: ");
    }
}
