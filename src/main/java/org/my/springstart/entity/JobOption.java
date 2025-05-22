package org.my.springstart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class JobOption {
    private List<String> jobList;   //职位列表
    private List<Long> dataList;  //各职位对应的总人数
}
