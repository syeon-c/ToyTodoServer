package org.zerock.api01.todo.mapper;

import org.zerock.api01.todo.dto.FileAddDTO;
import org.zerock.api01.todo.dto.FileDTO;

import java.time.LocalDate;
import java.util.List;

public interface FileMapper {

    void addFiles(FileAddDTO fileAddDTO);

    List<String> getDeletedFiles(LocalDate date);

}
