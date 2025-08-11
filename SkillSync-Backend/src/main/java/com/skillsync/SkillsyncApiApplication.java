package com.skillsync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {
    "com.skillsync.controller",
    "com.skillsync.service",
    "com.skillsync.repository",
    "com.skillsync.model"
})
public class SkillsyncApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkillsyncApiApplication.class, args);
    }
}
