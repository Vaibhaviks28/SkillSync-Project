package com.skillsync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.skillsync.controller",
    "com.skillsync.service",
    "com.skillsync.repository",
    "com.skillsync.model"
})
@EnableWebMvc
public class SkillsyncApiApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SkillsyncApiApplication.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3001")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowCredentials(true)
            .allowedHeaders("*");
    }
}
