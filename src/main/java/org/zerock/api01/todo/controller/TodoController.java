package org.zerock.api01.todo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.api01.common.dto.PageResponseDTO;
import org.zerock.api01.todo.dto.FileAddDTO;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.dto.TodoFileDTO;
import org.zerock.api01.todo.dto.TodoRequestDTO;
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
        log.info(todoDTO);
        todoService.updateTodo(todoDTO);
    }

    @PostMapping("")
    public void addTodo(@RequestBody TodoFileDTO todoFileDTO) {
        log.info("=======Add Todo======");


        TodoDTO todoDTO = todoFileDTO.getTodoDTO();

        todoService.addTodo(todoDTO);

        FileAddDTO fileAddDTO = FileAddDTO.builder()
                .tno(todoDTO.getTno())
                .fnames(todoFileDTO.getFileNames())
                .build();


        fileService.addFiles(fileAddDTO);

    }

}
