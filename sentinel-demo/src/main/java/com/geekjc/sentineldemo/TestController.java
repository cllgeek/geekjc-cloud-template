package com.geekjc.sentineldemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ll
 * @date 2019年10月14日 11:19 AM
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello, sentinel dashborad";
    }
}
