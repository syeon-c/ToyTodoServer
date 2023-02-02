package org.zerock.api01.todo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class TodoFileDTO {

    TodoDTO todoDTO;

    private List<String> fileNames;


//    private List<String> fileNames;
//
//    public TodoFileDTO(Long tno, String title, String writer) {
//        super(tno, title, writer);
//    }
}
