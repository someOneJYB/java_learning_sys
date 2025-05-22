package org.my.springstart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpLoginInfo {
    private Integer id;
    private String username;
    private String name;
    // 对应 jwt
    private String token;
}

