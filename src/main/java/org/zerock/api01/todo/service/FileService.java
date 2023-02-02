package org.zerock.api01.todo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.todo.dto.FileDTO;

public interface FileService {

    void addFiles(FileDTO fileDTO);

}
