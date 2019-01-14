package com.cpscs.omszuul.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cpscs.omszuul.service.consumer.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpscs.common.constants.CommonConstants;
import com.cpscs.common.context.FilterContextHandler;
import com.cpscs.common.dto.MenuDTO;
import com.cpscs.common.dto.UserToken;
import com.cpscs.common.utils.JSONUtils;
import com.cpscs.common.utils.JwtUtils;
import com.cpscs.common.utils.Result;
import com.cpscs.omszuul.prc.admin.MenuService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @version V1.0
 */
public class AccessFilter extends ZuulFilter {
    @Autowired
    MenuService menuService;
    @Autowired
    RedisService redisService;

    private String ignorePath = "/api/sso/login,/api/admin/login";

    @Override
    public String filterType() {
        return "pre";    // 在请求被处理之后，会进入该过滤器
    }

    @Override
    public int filterOrder() {
        return 10000;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI();
        if (isStartWith(requestUri)) {
            return null;
        }
        String accessToken = request.getHeader(CommonConstants.CONTEXT_TOKEN);//Authorization
        if(null == accessToken || accessToken == ""){
            accessToken = request.getParameter(CommonConstants.TOKEN);//token
        }
        if (null == accessToken) {
            setFailedRequest(Result.error401(), 200);
            return null;
        }
        try {
            UserToken userToken = JwtUtils.getInfoFromToken(accessToken);
        } catch (Exception e) {
            setFailedRequest(Result.error401(), 200);
            return null;
        }
        FilterContextHandler.setToken(accessToken);
        if(!havePermission(request)){
            setFailedRequest(Result.error403(), 200);
            return null;
        }
        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
        //We need our JWT tokens relayed to resource servers
        //添加自己header
//        ctx.addZuulRequestHeader(CommonConstants.CONTEXT_TOKEN, accessToken);
        //移除忽略token
        headers.remove("authorization");
        return null;
//        RequestContext ctx = RequestContext.getCurrentContext();
//        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
//        // We need our JWT tokens relayed to resource servers
//        headers.remove("authorization");
//        return null;
    }

    private void setFailedRequest(Object body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        HttpServletResponse response = ctx.getResponse();
        PrintWriter out = null;
        try{
            out = response.getWriter();
            out.write(JSONUtils.beanToJson(body));
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
        ctx.setSendZuulResponse(false);
    }

    private boolean havePermission(HttpServletRequest request){
        String currentURL = request.getRequestURI();
        List<MenuDTO> menuDTOS = menuService.userMenus();
        System.out.println(Arrays.toString(menuDTOS.toArray()));
        for(MenuDTO menuDTO:menuDTOS){
            if(currentURL!=null&&null!=menuDTO.getUrl()&&currentURL.startsWith(menuDTO.getUrl())){
                System.out.println(currentURL+"----:----"+menuDTO.getUrl());

                //如果数据库中的method是ALL，那么直接放行
                if("ALL".equalsIgnoreCase(menuDTO.getMethod())){
                    return true;
                }
                //menudto里的url是目录访问权限，因为restful风格api，如果请求方式不对，也没有具体的功能权限
                if(menuDTO.getMethod()!=null&&menuDTO.getMethod().equalsIgnoreCase(request.getMethod())){
                    return true;
                }

                //暂时是true，之后会改
                return false;
            }
        }
        return false;
    }

    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : ignorePath.split(",")) {

            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }
}
