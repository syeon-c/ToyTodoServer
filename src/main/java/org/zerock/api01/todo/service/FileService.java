package org.zerock.api01.todo.service;

import org.zerock.api01.todo.dto.FileAddDTO;
import org.zerock.api01.todo.dto.FileDTO;
import org.zerock.api01.todo.dto.FileInfoDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FileService {

    void addFiles(List<FileInfoDTO> infoDTOList, Long tno);

    List<String> getDeletedFiles(LocalDate date);

}
