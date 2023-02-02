package org.zerock.api01.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private Long fno;

    private String fname;

    private Long tno;
}
