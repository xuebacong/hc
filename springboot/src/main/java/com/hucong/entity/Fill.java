package com.hucong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fill {
    private Integer fillId;
    private String fillQuestion;
    private String fillAnswer;
}
