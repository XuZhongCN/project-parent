package com.cpscs.common.hystrix;

import com.cpscs.common.utils.Result;

/**
 *
 * @Description 通用的熔斷方法
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/11 0011
 */
public class Fallback {
    public static String badGateway(){
        Result.error(502,"服务被熔断了");
        return null;
    }
}
