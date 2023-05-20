package top.xy23.ad_springboot.utils;

import top.xy23.ad_springboot.enums.ResultCode;
import top.xy23.ad_springboot.enums.RetCodeEnums;

import java.util.HashMap;


public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", RetCodeEnums.OK.getCode());
        put("msg", "success");
    }

    public static R err() {
        return defined(RetCodeEnums.ERROR.getCode(), "系统异常");
    }

    public static R err(String msg) {
        return defined(RetCodeEnums.ERROR.getCode(), msg);
    }

    public static R err(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R defined(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R defined(ResultCode resultCode) {
        R r = new R();
        r.put("code", resultCode.getCode());
        r.put("msg", resultCode.getMessage());
        return r;
    }

    public static R defined(ResultCode resultCode, Object data) {
        R r = new R();
        r.put("code", resultCode.getCode());
        r.put("msg", resultCode.getMessage());
        r.put("data", data);
        return r;
    }

    public static R failure(String msg) {
        R r = new R();
        r.put("code", RetCodeEnums.FAIL.getCode());
        r.put("msg", msg);
        return r;
    }

    public static R failure(String msg, Object data) {
        R r = new R();
        r.put("code", RetCodeEnums.FAIL.getCode());
        r.put("msg", msg);
        r.put("data", data);
        return r;
    }


    public static R other(String msg) {
        R r = new R();
        r.put("code", RetCodeEnums.OTHER.getCode());
        r.put("msg", msg);
        return r;
    }

    public static R unLogin() {
        R r = new R();
        r.put("code", RetCodeEnums.UNLOGIN.getCode());
        r.put("msg", "用户未登录");
        return r;
    }

    public static R disabled(String msg) {
        R r = new R();
        r.put("code", RetCodeEnums.DISABLED.getCode());
        r.put("msg", msg);
        return r;
    }

    public static R unAuth() {
        R r = new R();
        r.put("code", RetCodeEnums.UNAUTH.getCode());
        r.put("msg", "请求无访问权限");
        return r;
    }

    public static R param(String msg) {
        R r = new R();
        r.put("code", RetCodeEnums.PARAM.getCode());
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.put("data", data);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public static R ok(int code, Object data) {
        R r = new R();
        r.put("code", code);
        r.put("data", data);
        return r;
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
