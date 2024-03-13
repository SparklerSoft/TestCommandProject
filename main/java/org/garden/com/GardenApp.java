package org.garden.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration.class
//})
public class GardenApp {

    public static void main(String[] args) {
        SpringApplication.run(GardenApp.class, args);
    }
}