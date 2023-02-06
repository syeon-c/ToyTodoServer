package org.zerock.api01.todo.mapper;

import org.zerock.api01.todo.dto.FileAddDTO;
import org.zerock.api01.todo.dto.FileDTO;

public interface FileMapper {

    void addFiles(FileAddDTO fileAddDTO);

}
