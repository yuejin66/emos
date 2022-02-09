package com.yuejin66.emoswxapi.common.util;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuejin66
 */
public class Result extends HashMap<String, Object> {

    public Result() {
        put("code", HttpStatus.SC_OK);
        put("msg", "success");
    }

    // 链式调用，可以连续进行 put
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static Result success() {
        return new Result();
    }

    public static Result success(String msg) {
        Result result = new Result();
        result.put("msg", msg);
        return result;
    }

    public static Result success(Map<String, Object> map) {
        Result result = new Result();
        result.putAll(map);
        return result;
    }

    public static Result error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知错误，请联系管理员");
    }

    public static Result error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

}
