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

        return condition.trim().length() == 0 ? null : condition.split(",");

    }

    public String getKeyword() {

        return keyword.trim().length() == 0 ? null : keyword;

    }
}
