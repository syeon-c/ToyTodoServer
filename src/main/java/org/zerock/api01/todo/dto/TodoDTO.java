package org.zerock.api01.todo.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@AllArgsConstructor
@Alias("TodoDTO")
public class TodoDTO {

    private Long tno;

    private String title;

    private String writer;
}
