package org.zerock.api01.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "org.zerock.api01.**.mapper")
public class RootConfig {
}
