package org.zerock.api01.todo.service;

import org.zerock.api01.common.dto.PageRequestDTO;
import org.zerock.api01.common.dto.PageResponseDTO;
import org.zerock.api01.todo.dto.*;

public interface TodoService {

    TodoFileDetailDTO getTodo(Long id);

    PageResponseDTO<TodoListDTO> getList(TodoRequestDTO todoRequestDTO);

    void deleteTodo(Long id);

    void updateTodo(TodoModDTO todoModDTO);

    void addTodo(TodoDTO todoDTO);
}
