package com.geekjc.nacosconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class NacosConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }

    @RestController
    @RefreshScope
    static class TestController {
        @Value("${config.title:}")
        private String title;

        @Value("${config.name:}")
        private String name;

        @Value("${config.account:}")
        private String account;

        @GetMapping("/test")
        public String Hello() {
            return title;
        }

        @GetMapping("/geekjc")
        public String Geekjc() {
            return name;
        }

        @GetMapping("/account")
        public String Account() {
            return account;
        }
    }

}


