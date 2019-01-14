package com.cpscs.omssso.controller;

import com.cpscs.common.annotation.Log;
import com.cpscs.common.context.FilterContextHandler;
import com.cpscs.common.dto.LoginDTO;
import com.cpscs.common.dto.UserToken;
import com.cpscs.common.utils.JSONUtils;
import com.cpscs.common.utils.JwtUtils;
import com.cpscs.common.utils.Result;


import com.cpscs.omssso.domain.UserDO;
import com.cpscs.omssso.service.MenuService;
import com.cpscs.omssso.service.TokenService;
import com.cpscs.omssso.service.UserService;
import com.cpscs.omssso.service.consumer.RedisService;
import com.cpscs.omssso.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */
@RequestMapping()
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MenuService menuService;


    /**
    *  单点登录逻辑，token被放在redis里
    *
    * */

    @Autowired
    private RedisService redisService;

    @Log("登录")
    @PostMapping("/login")
    Result login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        UserDO userDO=null;
        String token=request.getHeader("Authorization");

        String json=redisService.get(token);
        if(json==null){
            //缓存中没有数据
            String username = loginDTO.getUsername().trim();
            String password = loginDTO.getPwd().trim();
            password = MD5Utils.encrypt(username, password);
            System.out.println("================"+password);
            Map<String, Object> param = new HashMap<>();
            param.put("username", username);
            List<UserDO> userDOs = userService.list(param);
            if(userDOs.size()<1){
                return Result.error("用户或密码错误");
            }
            userDO = userDOs.get(0);
            if (null == userDO || !userDO.getPassword().equals(password)) {
                return Result.error("用户或密码错误");
            }
            UserToken userToken = new UserToken(userDO.getUsername(), userDO.getUserId().toString(), userDO.getName());

            try {
                token = JwtUtils.generateToken(userToken, 2*60*60*1000);
                redisService.put(token,JSONUtils.beanToJson(userDO),2*60*60*1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //缓存有数据
            userDO= (UserDO) JSONUtils.jsonToBean(json,UserDO.class);

        }
        //首先清除用户缓存权限
        menuService.clearCache(userDO.getUserId());
        return Result.ok("登录成功")
                .put("token", token).put("user",userDO)
                .put("perms",menuService.PermsByUserId(userDO.getUserId()))
                .put("router",menuService.RouterDTOsByUserId(userDO.getUserId()));
    }
    @RequestMapping("/logout")
    Result logout(HttpServletRequest request, HttpServletResponse response) {
        menuService.clearCache(Long.parseLong(FilterContextHandler.getUserID()));
        return Result.ok();
    }
}
