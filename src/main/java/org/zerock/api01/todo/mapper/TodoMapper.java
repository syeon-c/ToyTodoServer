package org.zerock.api01.todo.mapper;

import org.zerock.api01.common.dto.PageRequestDTO;
import org.zerock.api01.common.dto.PageResponseDTO;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.dto.TodoFileDetailDTO;
import org.zerock.api01.todo.dto.TodoListDTO;
import org.zerock.api01.todo.dto.TodoRequestDTO;

import java.util.List;

public interface TodoMapper {

    TodoFileDetailDTO getTodo(Long id);

    List<TodoListDTO> getList(TodoRequestDTO todoRequestDTO);

    int getCount(TodoRequestDTO todoRequestDTO);

    void deleteTodo(Long id);

    void updateTodo(TodoDTO todoDTO);

    void addTodo(TodoDTO todoDTO);

    String getFileName(Long tno);


}
