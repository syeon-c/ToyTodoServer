package org.zerock.api01.common.util.service;

import org.zerock.api01.common.dto.FileDTO;

import java.util.List;

public interface MinioService {

    List<FileDTO> getListObjects();

    FileDTO uploadFile(FileDTO fileDTO);

}
