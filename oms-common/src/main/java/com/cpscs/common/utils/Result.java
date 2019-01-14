package com.cpscs.common.utils;

import java.util.HashMap;
import java.util.Map;

public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", 0);
        put("msg", "操作成功");
    }

    public static Result error() {
        return error(500, "操作失败");
    }

    public static Result operate(boolean b){
        if(b){
            return Result.ok();
        }
        return Result.error();
    }

    public static Result error(String msg) {
        return error(500, msg);
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    public static Result ok(String msg) {
        Result result = new Result();
        result.put("msg", msg);
        return result;
    }

    public static Result ok(Map<String, Object> map) {
        Result result = new Result();
        result.putAll(map);
        return result;
    }

    public static Result ok() {
        return new Result();
    }

    public static Result error401() {
        return error(401, "你还没有登录");
    }

    public static Result error403() {
        return error(403, "你没有访问权限");
    }

    public static Result data(Object data){
        return Result.ok().put("data",data);
    }

    public static Result page(Object page){
        return Result.ok().put("page",page);
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
