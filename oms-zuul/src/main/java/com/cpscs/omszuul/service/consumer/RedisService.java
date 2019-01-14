package com.cpscs.omszuul.service.consumer;

import com.cpscs.omszuul.service.consumer.fallback.RedisServiceFallback;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/11 0011
 */
@Headers("Content-Type:application/json")
@FeignClient(value="oms-redis",fallback= RedisServiceFallback.class)
public interface RedisService {
    @RequestMapping(value="put",method= RequestMethod.PUT)
     String put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value, @RequestParam(value = "seconds") long seconds);
    @RequestMapping(value="get",method= RequestMethod.GET)
     String get(@RequestParam(value = "key") String key);
}
