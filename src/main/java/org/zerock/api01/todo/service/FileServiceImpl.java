package org.zerock.api01.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.todo.dto.FileAddDTO;
import org.zerock.api01.todo.dto.FileDTO;
import org.zerock.api01.todo.dto.FileInfoDTO;
import org.zerock.api01.todo.mapper.FileMapper;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    @Override
    public void addFiles(List<FileInfoDTO> infoDTOList, Long tno) {

        FileAddDTO fileAddDTO = FileAddDTO.builder()
                .tno(tno)
                .fileInfo(infoDTOList)
                .build();

        log.info("addFiles: " + fileAddDTO);

        fileMapper.addFiles(fileAddDTO);
    }

    @Override
    public List<String> getDeletedFiles(LocalDate date) {
        return fileMapper.getDeletedFiles(date);
    }
}
