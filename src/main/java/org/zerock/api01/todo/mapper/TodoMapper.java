package org.zerock.api01.todo.mapper;

import org.zerock.api01.common.dto.PageRequestDTO;
import org.zerock.api01.common.dto.PageResponseDTO;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.dto.TodoRequestDTO;

import java.util.List;

public interface TodoMapper {

    TodoDTO getTodo(Long id);

    List<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    List<TodoDTO> getList(TodoRequestDTO todoRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

    void deleteTodo(Long id);

    void updateTodo(TodoDTO todoDTO);

    void addTodo(TodoDTO todoDTO);


}
