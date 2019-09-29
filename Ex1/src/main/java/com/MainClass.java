package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Cat on 27.09.2019.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com", "com.Controllers", "com.Beans"})
public class MainClass {
    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);
    }
}
