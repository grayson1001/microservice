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
package com.example.configcustomer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * HelloController
 *
 * @Description:
 * @Author: grayson
 * @Version 1.0
 * @Date: 2021-04-20 17:43
 **/
@RestController
public class HelloController {

    private static final String applicationName = "eureka-client-provider";

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/index")
    public String getHello(){
        String url = "http://"+ applicationName +"/user/hello";
        return  restTemplate.getForObject(url, String.class);
    }


    @Value("${word}")
    private String word;

    @RequestMapping("/create")
    public String index(@RequestParam String name) {
        return name+","+ this.word;
    }
}
