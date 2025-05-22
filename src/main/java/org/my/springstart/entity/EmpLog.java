package org.my.springstart.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EmpLog {
 private Integer id;
 private LocalDateTime operateTime; //操作时间
 private String info;
}
