package com.cpscs.omssso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/10 0010
 */
@EnableFeignClients(basePackages = {"com.cpscs"})
@EnableCaching
@EnableScheduling
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.cpscs.omssso", "com.cpscs.common"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class OmsSSOApplication {
    public static void main(String[] args) {
        SpringApplication.run(OmsSSOApplication.class, args);
    }
}
