package org.zerock.api01.todo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class TodoFileDTO {

    TodoDTO todoDTO;

    private List<FileInfoDTO> fileInfo;

    @Override
    public String toString() {
        return "TodoFileDTO{" +
                "todoDTO=" + todoDTO +
                ", fileInfo=" + fileInfo +
                '}';
    }

    //    private List<String> fileNames;
//
//    public TodoFileDTO(Long tno, String title, String writer) {
//        super(tno, title, writer);
//    }
}
