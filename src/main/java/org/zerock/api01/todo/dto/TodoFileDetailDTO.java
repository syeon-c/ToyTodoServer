package org.zerock.api01.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoFileDetailDTO {

    private Long tno;

    private String title;

    private String writer;

    private List<FileDTO> files;



}
