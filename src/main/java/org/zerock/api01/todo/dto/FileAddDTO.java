package org.zerock.api01.todo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileAddDTO {

    private Long tno;

    private List<FileInfoDTO> fileInfo;

    @Override
    public String toString() {
        return "FileAddDTO{" +
                "tno=" + tno +
                ", fileInfo=" + fileInfo +
                '}';
    }
}

