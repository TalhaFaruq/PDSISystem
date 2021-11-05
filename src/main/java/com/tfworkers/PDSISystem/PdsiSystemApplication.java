package com.tfworkers.PDSISystem;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
public class PdsiSystemApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(PdsiSystemApplication.class, args);

	}

}
