package org.zerock.api01.todo.dto;

import lombok.*;

import java.util.List;

/** Todo 게시글 추가 요청 DTO **/
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
