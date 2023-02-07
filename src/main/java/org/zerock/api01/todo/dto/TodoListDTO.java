package org.zerock.api01.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.checkerframework.checker.units.qual.A;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("TodoListDTO")
public class TodoListDTO {

    @JsonProperty("id")
    private Long tno;

    private String title;

    private String writer;

    private String fname;

}
