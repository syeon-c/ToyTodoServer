package org.zerock.api01.todo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private Long fno;

    private String fname;

    private Long tno;

    private Boolean isMain;
}
