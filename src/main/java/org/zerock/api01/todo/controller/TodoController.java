package org.zerock.api01.todo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.api01.common.dto.PageRequestDTO;
import org.zerock.api01.common.dto.PageResponseDTO;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.dto.TodoRequestDTO;
import org.zerock.api01.todo.mapper.TodoMapper;
import org.zerock.api01.todo.service.TodoService;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/todos/")
public class TodoController {

    private final TodoService todoService;

//    @GetMapping("list")
//    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO){
//        log.info(pageRequestDTO);
//        log.info("=============ReqDTO==============");
//        log.info(pageRequestDTO.getPage());
//        log.info(pageRequestDTO.getSize());
//
//        PageResponseDTO<TodoDTO> result = todoService.getList(pageRequestDTO);
//
//        return result;
//    }

    @GetMapping("list")
    public PageResponseDTO<TodoDTO> getSearchList(TodoRequestDTO todoRequestDTO) {
        log.info(todoRequestDTO);
        log.info("===========TodoReqDTO==========");
        log.info(todoRequestDTO.getKeyword());
        log.info(todoRequestDTO.getCondition());
        log.info(todoRequestDTO.getPage());
        log.info(todoRequestDTO.getSize());

        PageResponseDTO<TodoDTO> result = todoService.getList(todoRequestDTO);
        return result;
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

    @PutMapping("")
    public void updateTodo(@RequestBody TodoDTO todoDTO) {
        log.info("=======UpdateTodo======");
        todoService.updateTodo(todoDTO);
    }

    @PostMapping("")
    public void addTodo(@RequestBody TodoDTO todoDTO) {
        log.info("=======Add Todo======");
        todoService.addTodo(todoDTO);
    }

}
