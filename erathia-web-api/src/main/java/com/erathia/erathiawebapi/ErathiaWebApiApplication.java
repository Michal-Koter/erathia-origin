package com.erathia.erathiawebapi;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ErathiaWebApiApplication{

    public static void main(String[] args) {
        SpringApplication.run(ErathiaWebApiApplication.class, args);
    }

}
