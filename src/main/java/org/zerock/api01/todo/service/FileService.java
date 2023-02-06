package org.zerock.api01.todo.service;

import org.zerock.api01.todo.dto.FileAddDTO;
import org.zerock.api01.todo.dto.FileDTO;

public interface FileService {

    void addFiles(FileAddDTO fileAddDTO);

}
