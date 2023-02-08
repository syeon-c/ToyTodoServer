package org.zerock.api01.todo.dto;

import lombok.*;

/** 첨부파일 정보 관련 DTO **/
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class FileInfoDTO {

    private String fname;

    private Boolean isMain;

}
