package org.my.springstart.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    // 快速创建异常的静态方法
    public static BusinessException of(int code, String message) {
        return new BusinessException(code, message);
    }
}
