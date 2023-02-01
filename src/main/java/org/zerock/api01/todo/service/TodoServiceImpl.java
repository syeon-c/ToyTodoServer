package org.zerock.api01.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.common.dto.PageRequestDTO;
import org.zerock.api01.common.dto.PageResponseDTO;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.dto.TodoRequestDTO;
import org.zerock.api01.todo.mapper.TodoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;

    @Override
    public TodoDTO getTodo(Long id) {

        log.info("getTodo: " + id);

        return todoMapper.getTodo(id);
    }


    @Override
    public void addTodo(TodoDTO todoDTO) {
        todoMapper.addTodo(todoDTO);
        log.info(todoDTO);
    }

    @Override
    public void updateTodo(TodoDTO todoDTO) {
        todoMapper.updateTodo(todoDTO);
        log.info(todoDTO);
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(TodoRequestDTO todoRequestDTO) {

        List<TodoDTO> dtoList = todoMapper.getList(todoRequestDTO);

        int count = todoMapper.getCount(todoRequestDTO);

        PageResponseDTO<TodoDTO> result = PageResponseDTO.<TodoDTO>withAll()
                .pageRequestDTO(todoRequestDTO)
                .dtoList(dtoList)
                .total(count)
                .build();

        return result;

    }
    @Override
    public void deleteTodo(Long id) {

        log.info("deleteTodo: " + id);

        todoMapper.deleteTodo(id);
    }
}
