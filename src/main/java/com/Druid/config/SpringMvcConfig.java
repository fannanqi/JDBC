package com.Druid.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.Druid.controller","com.Druid.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
