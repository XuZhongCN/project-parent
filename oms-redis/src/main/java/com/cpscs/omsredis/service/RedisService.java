package com.cpscs.omsredis.service;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/11 0011
 */
public interface RedisService {
    public void set(String key, Object value, long seconds);
    public Object get(String key);
}