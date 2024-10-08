package com.sparta.myscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MySchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySchedulerApplication.class, args);
    }

}
