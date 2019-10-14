package com.geekjc.nacosdiscoveryconsumer;

import com.geekjc.nacosdiscoveryconsumer.NacosDiscoveryConsumerApplication.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ll
 * @date 2019年10月11日 11:13 AM
 */
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EchoService echoService;

    @GetMapping(value = "/echo-rest/{string}")
    public String rest(@PathVariable String string) {
        return restTemplate.getForObject("http://nacos-provider/echo/" + string, String.class);
    }
    @GetMapping(value = "/echo-feign/{string}")
    public String feign(@PathVariable String string) {
        return echoService.echo(string);
    }

}
