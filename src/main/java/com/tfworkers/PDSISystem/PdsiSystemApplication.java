package com.tfworkers.PDSISystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The type Pdsi system application.
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class PdsiSystemApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SpringApplication.run(PdsiSystemApplication.class, args);
    }

}
