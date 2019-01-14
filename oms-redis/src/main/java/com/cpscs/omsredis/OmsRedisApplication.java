package com.cpscs.omsredis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/11 0011
 */
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OmsRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(OmsRedisApplication.class, args);
    }
}