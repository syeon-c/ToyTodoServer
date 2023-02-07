package org.zerock.api01.todo.service;

import org.zerock.api01.todo.dto.FileAddDTO;
import org.zerock.api01.todo.dto.FileDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FileService {

    void addFiles(FileAddDTO fileAddDTO);

    List<String> getDeletedFiles(LocalDate date);

}
