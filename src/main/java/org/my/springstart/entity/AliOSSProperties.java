package org.my.springstart.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * 配置类
 */
@Data
@Component
// 获取在配置文件中的 aliyun.oss 的前缀的配置字段，就可以直接注入，@Value 就要写很多很多次，@ConfigurationProperties 就更佳简洁高效，只要在类上注解，相关对应字段属性就会注入
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {
    private String endpoint;
    private String bucket;
    private String accessKeyId;
    private String accessKeySecret;
    private LocalDateTime time;
}

