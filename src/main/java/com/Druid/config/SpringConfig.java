package com.Druid.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.Druid"})
@PropertySource("classpath:druid.properties")
@Import({JDBCUtils_Druid.class})
public class SpringConfig {
}
