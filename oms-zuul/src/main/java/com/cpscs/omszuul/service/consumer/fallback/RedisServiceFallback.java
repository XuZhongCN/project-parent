package com.cpscs.omszuul.service.consumer.fallback;

import com.cpscs.common.hystrix.Fallback;
import com.cpscs.omszuul.service.consumer.RedisService;
import org.springframework.stereotype.Component;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/11 0011
 */
@Component
public class RedisServiceFallback implements RedisService {

    @Override
    public String put(String key, String value, long seconds) {

        return Fallback.badGateway();
    }

    @Override
    public String get(String key) {
        return Fallback.badGateway();
    }
}
