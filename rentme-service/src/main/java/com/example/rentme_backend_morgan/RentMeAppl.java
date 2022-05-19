package com.example.rentme_backend_morgan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.example.rentme_backend_morgan.business.service.google")
@SpringBootApplication(
        scanBasePackages = {"com.example.rentme_backend_morgan", "com.example.googleservice"})

public class RentMeAppl {
    public static void main(String[] args) {
        SpringApplication.run(RentMeAppl.class, args);
    }
}



