package top.xy23.ad_springboot.enums;

public enum RetCodeEnums {
    OK(2000, "成功"),
    DISABLED(4005, "禁用"),
    FAIL(3000, "失败"),
    PARAM(4000, "参数有误"),
    UNLOGIN(4001, "未登录"),
    UNAUTH(4003, "未授权"),
    ERROR(5000, "系统异常"),
    OTHER(3003, "其它异常");

    int code;
    String desc;

    RetCodeEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
