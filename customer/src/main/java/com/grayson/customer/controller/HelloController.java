/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.grayson.customer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * IndexController
 *
 * @Description:
 * @Author: grayson
 * @Version 1.0
 * @Date: 2021-04-15 20:53
 **/
@RestController
public class HelloController {

    private static final String applicationName = "eureka-client-provider";

    @Autowired
    private RestTemplate restTemplate;

    //定制降级服务和超时
    @HystrixCommand(fallbackMethod = "getDefaultHello", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @RequestMapping("/index")
    public String getHello(){
        String url = "http://"+ applicationName +"/user/hello";
        return  restTemplate.getForObject(url, String.class);
    }

    //定制降级服务和线程池隔离策略
    @HystrixCommand(fallbackMethod = "getDefaultHi",
            threadPoolKey = "sayHiThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="30"),
                    @HystrixProperty(name = "maxQueueSize", value="50")
            }
    )
    public String sayHi(String name) {
        String url = "http://"+ applicationName +"/user/hi" + "?name=" + name;
        return restTemplate.getForObject(url ,String.class);
    }


    private String getDefaultHello(){
        return "hello";
    }

    private String getDefaultHi(){
        return "hi";
    }
}
