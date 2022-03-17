package com.hucong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleTest {
    private Integer testId;
    private List<Single> singleList;
    private List<Multiple> multipleList;
    private List<Fill> fillList;
}
