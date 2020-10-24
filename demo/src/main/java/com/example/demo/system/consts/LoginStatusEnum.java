package com.example.demo.system.consts;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 14:58
 * @desc
 */
public enum  LoginStatusEnum {

    LOGIN_SUCCESS(0, "登录成功"),
    EMPTY_INPUT(1, "请填写账号或密码"),
    INVALID_PWD(2, "账号或密码错误"),
    INVALID_USER(3, "账号或密码错误"),
    VCODE_ERROR(4, "验证码错误"),
    UNKNOWN_ERROR(5, "未知错误"),
    LOGOUT_SUCCESS(6, "退出登录"),
    NOT_ABLE(7, "当前账号已被禁");

    private Integer status;
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    LoginStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
