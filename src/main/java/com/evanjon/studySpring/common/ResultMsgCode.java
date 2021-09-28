package com.evanjon.studySpring.common;

public enum ResultMsgCode {
    SUCCESS(0, "success"),
    
    FAILURE(-1, "failure"),
    
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    
    AUTH_ACCESS_DENIED(3001, "拒绝访问"),
    AUTH_ACCESS_NO_RIGHT(3002, "未经授权"),
    
    ERROR_USER_NOT_EXISTS(4001, "用户不存在"),
    ERROR_CHANGE_PASSWORD_FAILED(4002, "更新密码失败"),
    ERROR_PARAMETERS(4003, "无效的参数"),
    ERROR_USER_ALREADY_EXISTS(4004, "账号已经存在"),
    
    ERROR_PORTL_LOST_HOST(5001, "无法连接portal"),
    ERROR_PORTL_GET_TOKEN(5002, "获取Token失败");
    
    private Integer code;
    private String message;

    ResultMsgCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
