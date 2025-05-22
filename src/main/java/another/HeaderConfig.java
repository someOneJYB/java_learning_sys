package another;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderConfig {

    @Bean
    @ConditionalOnClass(name = "another.ServiceC")  //检查JVM环境中是否存在ServiceC的字节码
    public HeaderParser headerParser(){
        return new HeaderParser();
    }

    @Bean
    @ConditionalOnMissingBean   //检查环境中是否存在指定的bean，不指定名字就是只当前被修饰的bean，在类库中使用的比较多
    public HeaderGenerator headerGenerator(){
        return new HeaderGenerator();
    }

    @Bean
    @ConditionalOnProperty(name = "aaa", havingValue = "666")    //检查配置文件中 yml 环境中是否存在指定的属性和值，需要再 yml 中配置 key aaa: 666 执行就可以被识别到，
    public TokenParser tokenParser(){
        return new TokenParser();
    }
}
