package org.zerock.api01.todo.service;

import org.zerock.api01.todo.dto.TodoDTO;

public interface TodoService {

    public TodoDTO getTodo(Long id);

    public void deleteTodo(Long id);
}
