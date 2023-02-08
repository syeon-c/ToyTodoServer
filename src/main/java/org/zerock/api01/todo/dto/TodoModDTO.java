package org.zerock.api01.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** Todo Modify 요청 DTO **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoModDTO {

    private Long tno;

    private String title;

    private Long mainFno;

    private List<Long> removedFnos;

    private List<FileInfoDTO> addedFileNames;

}
