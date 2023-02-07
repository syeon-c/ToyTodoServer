package org.zerock.api01.todo.service;

import org.zerock.api01.common.dto.PageRequestDTO;
import org.zerock.api01.common.dto.PageResponseDTO;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.dto.TodoFileDetailDTO;
import org.zerock.api01.todo.dto.TodoListDTO;
import org.zerock.api01.todo.dto.TodoRequestDTO;

public interface TodoService {

    TodoFileDetailDTO getTodo(Long id);

    PageResponseDTO<TodoListDTO> getList(TodoRequestDTO todoRequestDTO);

    void deleteTodo(Long id);

    void updateTodo(TodoDTO todoDTO);

    void addTodo(TodoDTO todoDTO);
}
