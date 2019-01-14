package com.cpscs.omsorderbill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients(basePackages = {"com.cpscs"})
@EnableCaching
@EnableScheduling
@MapperScan(value = "com.cpscs.omsorderbill.dao")
@SpringBootApplication(scanBasePackages = {"com.cpscs.omsorderbill", "com.cpscs.common"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class CluddoOrderBillApplication {

    public static void main(String[] args) {
        SpringApplication.run(CluddoOrderBillApplication.class, args);
    }

}
