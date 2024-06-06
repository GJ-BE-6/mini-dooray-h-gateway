package com.nhnacademy.minidorray_gateway;

import com.nhnacademy.minidorray_gateway.properties.RedisProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({RedisProperties.class})
public class MinidorrayGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinidorrayGatewayApplication.class, args);
    }

}
