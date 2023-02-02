package org.zerock.api01.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.todo.dto.FileDTO;
import org.zerock.api01.todo.mapper.FileMapper;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    @Override
    public void addFiles(FileDTO fileDTO) {

        log.info("addFiles: " + fileDTO);

        fileMapper.addFiles(fileDTO);
    }
}
