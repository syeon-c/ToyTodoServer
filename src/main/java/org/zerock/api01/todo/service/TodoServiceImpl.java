package org.zerock.api01.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.common.dto.PageRequestDTO;
import org.zerock.api01.common.dto.PageResponseDTO;
import org.zerock.api01.todo.dto.*;
import org.zerock.api01.todo.mapper.FileMapper;
import org.zerock.api01.todo.mapper.TodoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;

    private final FileMapper fileMapper;

    @Override
    public TodoFileDetailDTO getTodo(Long id) {

        log.info("getTodo: " + id);

        return todoMapper.getTodo(id);
    }


    @Override
    public void addTodo(TodoDTO todoDTO) {
        todoMapper.addTodo(todoDTO);
        log.info(todoDTO);
    }

    @Override
    public void updateTodo(TodoModDTO todoModDTO) {

        fileMapper.setMainFalse(todoModDTO.getTno());

        // Update Title
        todoMapper.updateTodo(todoModDTO);

        if (todoModDTO.getMainFno() != 0) {

            fileMapper.updateMain(todoModDTO.getMainFno());

        }

        // Update DeletedAt Date
        if (todoModDTO.getRemovedFnos().size() != 0) {
            fileMapper.softDeleteFiles(todoModDTO.getRemovedFnos());
        }

        // Update Added File List
        if (todoModDTO.getAddedFileNames().size() != 0) {
            FileAddDTO fileAddDTO = FileAddDTO.builder()
                    .tno(todoModDTO.getTno())
                    .fileInfo(todoModDTO.getAddedFileNames())
                    .build();

            fileMapper.addFiles(fileAddDTO);
        }

    }

    @Override
    public PageResponseDTO<TodoListDTO> getList(TodoRequestDTO todoRequestDTO) {

        List<TodoListDTO> dtoList = todoMapper.getList(todoRequestDTO);

        int count = todoMapper.getCount(todoRequestDTO);

        PageResponseDTO<TodoListDTO> result = PageResponseDTO.<TodoListDTO>withAll()
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
