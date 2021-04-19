package com.grayson.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client-provider", fallback = HelloServiceFeignFallBack.class)
public interface HelloServiceFeign {

    @RequestMapping (value = "/user/hello", method= RequestMethod.GET)
    public String sayHello();

    @RequestMapping (value = "/user/hi", method= RequestMethod.GET)
    public String sayHi(@RequestParam(value = "name") String name);
}

