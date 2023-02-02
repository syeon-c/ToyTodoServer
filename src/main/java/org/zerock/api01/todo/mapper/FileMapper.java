package org.zerock.api01.todo.mapper;

import org.zerock.api01.todo.dto.FileDTO;
import org.zerock.api01.todo.dto.TodoDTO;
import org.zerock.api01.todo.dto.TodoFileDTO;

public interface FileMapper {

    void addFiles(FileDTO fileDTO);

}
