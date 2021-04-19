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

import com.grayson.customer.feign.HelloServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 *
 * @Description:
 * @Author: grayson
 * @Version 1.0
 * @Date: 2021-04-19 00:54
 **/
@RestController
public class IndexController {

    @Autowired
    private HelloServiceFeign client;

    //@HystrixCommand(fallbackMethod = "getDefaultHello")
    @RequestMapping("/index")
    public String getHello(){
        return client.sayHello();
    }

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return client.sayHi(name);
    }

    public String getDefaultHello(){
        return "hello";
    }
}
