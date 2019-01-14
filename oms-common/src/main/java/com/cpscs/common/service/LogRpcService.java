package com.cpscs.common.service;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

import com.cpscs.common.dto.LogDO;
import com.cpscs.common.intercepter.FeignIntercepter;
import com.cpscs.common.utils.Result;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin", configuration = FeignIntercepter.class)
public interface LogRpcService {
    @Async
    @PostMapping("log/save")
    Result save(LogDO logDO);
}
