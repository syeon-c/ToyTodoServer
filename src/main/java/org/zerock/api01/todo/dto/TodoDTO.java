package org.zerock.api01.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Alias("TodoDTO")
public class TodoDTO {

    @JsonProperty("id")
    private Long tno;

    private String title;

    private String writer;
}
