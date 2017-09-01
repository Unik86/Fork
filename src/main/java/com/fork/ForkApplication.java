package com.fork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ForkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForkApplication.class, args);
    }
}
