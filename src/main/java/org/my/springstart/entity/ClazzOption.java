package org.my.springstart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class ClazzOption {
    private List clazzList; //职位列表
    private List dataList; //人数列表
}
