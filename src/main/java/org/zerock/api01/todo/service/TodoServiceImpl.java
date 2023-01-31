package org.zerock.api01.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.mapper.TodoMapper;

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
    public void deleteTodo(Long id) {

        log.info("deleteTodo: " + id);

        todoMapper.deleteTodo(id);
    }
}
