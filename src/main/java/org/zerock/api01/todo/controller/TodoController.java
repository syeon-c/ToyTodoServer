package org.zerock.api01.todo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.api01.common.dto.PageResponseDTO;
import org.zerock.api01.todo.dto.*;
import org.zerock.api01.todo.service.FileService;
import org.zerock.api01.todo.service.TodoService;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/todos/")

public class TodoController {

    private final TodoService todoService;

    private final FileService fileService;

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
    public PageResponseDTO<TodoListDTO> getSearchList(TodoRequestDTO todoRequestDTO) {
        log.info(todoRequestDTO);
        log.info("===========TodoReqDTO==========");
        log.info(todoRequestDTO.getKeyword());
        log.info(todoRequestDTO.getCondition());
        log.info(todoRequestDTO.getPage());
        log.info(todoRequestDTO.getSize());

        PageResponseDTO<TodoListDTO> result = todoService.getList(todoRequestDTO);
        return result;
    }

    @GetMapping("details/{id}")
    public TodoFileDetailDTO getTodo(@PathVariable Long id) {

        TodoFileDetailDTO todoDTO = todoService.getTodo(id);
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
        log.info(todoDTO);
        todoService.updateTodo(todoDTO);
    }

    @PostMapping("")
    public void addTodo(@RequestBody TodoFileDTO todoFileDTO) {
        log.info("=======Add Todo======");
        log.info(todoFileDTO);

        TodoDTO todoDTO = todoFileDTO.getTodoDTO();

        todoService.addTodo(todoDTO);

        if (todoFileDTO.getFileInfo().size() != 0) {

            fileService.addFiles(todoFileDTO.getFileInfo(), todoDTO.getTno());
        }

    }

}
