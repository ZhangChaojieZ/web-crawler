package com.zhang.webcrawleres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebcrawlerEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebcrawlerEsApplication.class, args);
    }

}
