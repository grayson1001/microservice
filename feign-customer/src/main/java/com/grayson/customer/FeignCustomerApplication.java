package com.grayson.customer;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignCustomerApplication.class, args);
	}

	/*@Bean
	public IRule ribbonRule(){
		return new RandomRule();
	}*/

}
