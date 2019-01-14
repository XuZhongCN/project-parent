package com.cpscs.common.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.cpscs.common.annotation.Log;
import com.cpscs.common.context.FilterContextHandler;
import com.cpscs.common.dto.LogDO;
import com.cpscs.common.service.LogRpcService;
import com.cpscs.common.utils.HttpContextUtils;
import com.cpscs.common.utils.IPUtils;
import com.cpscs.common.utils.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    LogRpcService logService;


    @Pointcut("@annotation(com.cpscs.common.annotation.Log)")
    //@Pointcut("execution( * com.cpscs..controller.*.*(..))")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point, time);
        return result;
    }

    void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogDO sysLog = new LogDO();
        Log syslog = method.getAnnotation(Log.class);
        if (syslog != null) {
            // 注解上的描述
            sysLog.setOperation(syslog.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        List reqArgs = new ArrayList();
        // RequestFacade
        if(args.length!=0){
        	for(Object obj : args){
        		if(obj instanceof HttpServletRequest){
        			
        		} else if(obj instanceof HttpServletResponse){
        			
        		} else if(obj instanceof CommonsMultipartFile){
        			
        		} else {
        			reqArgs.add(obj);
        		}
        	}
        }
        if(reqArgs.size()!=0){
        	//JSONArray fromObject = JSONArray.fromObject(args);
            //String params = fromObject.toString();
        	 String params = JSON.toJSONString(reqArgs);
            if(StringUtils.isNotEmpty(params)){
            	sysLog.setParams(params);
            }else{
                return;
            }

        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 用户名
        //SysUser user =  (SysUser) request.getSession().getAttribute(SessionAttr.USER_LOGIN.getValue());
        sysLog.setUserId(Long.parseLong(FilterContextHandler.getUserID() == null ? "000000" : FilterContextHandler.getUserID()));
        sysLog.setUsername(FilterContextHandler.getUsername() == null ? "" : FilterContextHandler.getUsername());
        sysLog.setTime((int) time);
        // 系统当前时间
        Date date = new Date();
        sysLog.setGmtCreate(date);
        // 保存系统日志
        logService.save(sysLog);
    }
}

