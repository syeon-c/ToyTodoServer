package org.zerock.api01.todo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class FileInfoDTO {

    private String fname;

    private Boolean isMain;

}
