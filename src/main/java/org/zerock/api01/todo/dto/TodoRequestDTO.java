package org.zerock.api01.todo.dto;

import lombok.*;
import org.zerock.api01.common.dto.PageRequestDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestDTO extends PageRequestDTO {

    String keyword;

    String condition;

    public String[] getCondition() {
        return condition.split(",");
    }
}
