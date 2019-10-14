package com.geekjc.nacosdiscoveryconsumer;

import com.geekjc.nacosdiscoveryconsumer.NacosDiscoveryConsumerApplication.EchoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosDiscoveryConsumerApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @FeignClient(name = "nacos-provider", fallback = EchoServiceFallback.class,
            configuration = FeignConfiguration.class)
    public interface EchoService {
        @GetMapping(value = "/echo/{string}")
        String echo(@PathVariable("string") String string);

        @GetMapping("/divide")
        String divide(@RequestParam("a") Integer a, @RequestParam("b") Integer b);

        default String divide(Integer a) {
            return divide(a, 0);
        }

        @GetMapping("/notFound")
        String notFound();
    }

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryConsumerApplication.class, args);
    }

}

class FeignConfiguration {

    @Bean
    public EchoServiceFallback echoServiceFallback() {
        return new EchoServiceFallback();
    }

}

class EchoServiceFallback implements EchoService {

    @Override
    public String echo(@PathVariable("str") String str) {
        return "echo fallback";
    }

    @Override
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        return "divide fallback";
    }

    @Override
    public String notFound() {
        return "notFound fallback";
    }

}