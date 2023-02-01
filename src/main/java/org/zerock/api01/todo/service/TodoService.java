package org.zerock.api01.todo.service;

import org.zerock.api01.common.dto.PageRequestDTO;
import org.zerock.api01.common.dto.PageResponseDTO;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.dto.TodoRequestDTO;

public interface TodoService {

    TodoDTO getTodo(Long id);

    PageResponseDTO<TodoDTO> getList(TodoRequestDTO todoRequestDTO);

    void deleteTodo(Long id);

    void updateTodo(TodoDTO todoDTO);

    void addTodo(TodoDTO todoDTO);
}
