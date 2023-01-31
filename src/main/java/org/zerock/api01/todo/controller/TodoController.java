package org.zerock.api01.todo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.api01.common.dto.PageRequestDTO;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.mapper.TodoMapper;
import org.zerock.api01.todo.service.TodoService;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/todos/")
public class TodoController {


    private final TodoService todoService;

    @GetMapping("list")
    public String[] getList(PageRequestDTO pageRequestDTO){
        log.info(pageRequestDTO);
        return new String[]{"AAA","BBB","CCC"};
    }

    @GetMapping("details/{id}")
    public TodoDTO getOne(@PathVariable Long id) {

        TodoDTO todoDTO = todoService.getTodo(id);
        log.info("get one: " + todoDTO);


        return todoDTO;
    }

    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable Long id) {

        log.info("deleteTodo: " + id);

        todoService.deleteTodo(id);
    }

}
