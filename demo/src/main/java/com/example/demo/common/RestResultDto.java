package com.example.demo.common;

import lombok.Data;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/6 9:44
 * @desc
 */
@Data
public class RestResultDto<T> {

    /**
     * 系统内部错误
     */
    public static final int ERROR_SYS = -1;
    /**
     * 成功
     */
    public static final int SUCCESS = 0;
    /**
     * 业务失败
     */
    public static final int FAIL_BIZ = 1;
    /**
     * 会话失败
     */
    public static final int FAIL_AUTH_SESSION = 2;
    /**
     * 需升级客户端
     */
    public static final int FAIL_UPGRADE = 3;
    /**
     * 鉴权失败
     */
    public static final int FAIL_AUTH = 4;


    /**
     * 返回码
     */
    private int code = SUCCESS;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public RestResultDto() {

    }

    public RestResultDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RestResultDto(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回执行成功结果
     *
     * @return
     */
    public static RestResultDto success() {
        return new RestResultDto();
    }

    /**
     * 返回执行成功结果
     *
     * @param msg 执行后的消息
     * @return
     */
    public static RestResultDto success(String msg) {
        RestResultDto result = new RestResultDto();
        result.setMsg(msg);

        return result;
    }

    /**
     * 返回执行成功结果
     *
     * @param data 返回的具体业务内容
     * @return
     */
    public static <T> RestResultDto<T> success(T data) {
        return success(null, data);
    }


    /**
     * 返回执行成功结果
     *
     * @param msg  执行后的消息
     * @param data 返回的具体业务内容
     * @return
     */
    public static <T> RestResultDto<T> success(String msg, T data) {
        RestResultDto result = new RestResultDto();
        result.setMsg(msg);
        result.setData(data);

        return result;
    }

    /**
     * @param status
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> RestResultDto<T> success(int status, String msg) {
        RestResultDto result = new RestResultDto();
        result.setMsg(msg);
        result.setCode(status);

        return result;
    }

    /**
     * 返回执行成功结果
     *
     * @param status
     * @param msg    执行后的消息
     * @param data   返回的具体业务内容
     * @return
     */
    public static <T> RestResultDto<T> success(int status, String msg, T data) {
        RestResultDto result = new RestResultDto();
        result.setCode(status);
        result.setMsg(msg);
        result.setData(data);

        return result;
    }

    /**
     * 返回执行失败结果
     *
     * @return
     */
    public static RestResultDto fail() {
        RestResultDto result = new RestResultDto();
        result.setCode(FAIL_BIZ);

        return result;
    }

    /**
     * 返回执行失败结果
     *
     * @param msg 执行后的消息
     * @return
     */
    public static RestResultDto fail(String msg) {
        RestResultDto result = new RestResultDto();
        result.setMsg(msg);
        result.setCode(FAIL_BIZ);

        return result;
    }

    /**
     * 返回执行失败结果
     *
     * @param data 返回的具体业务内容
     * @return
     */
    public static <T> RestResultDto<T> fail(T data) {
        RestResultDto result = new RestResultDto();
        result.setData(data);
        result.setCode(FAIL_BIZ);

        return result;
    }

    /**
     * 返回执行失败结果
     *
     * @param msg  执行后的消息
     * @param data 返回的具体业务内容
     * @return
     */
    public static <T> RestResultDto<T> fail(String msg, T data) {
        return new RestResultDto(FAIL_BIZ, msg, data);
    }

    /**
     * 返回执行失败结果
     *
     * @param status
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> RestResultDto<T> fail(int status, String msg) {
        return new RestResultDto(status, msg, null);
    }

    /**
     * 返回执行失败结果
     *
     * @param status
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResultDto<T> fail(int status, T data) {
        return new RestResultDto(status, null, data);
    }

    /**
     * 返回执行失败结果
     *
     * @param status 执行状态
     * @param msg    执行后的消息
     * @param data   返回的具体业务内容
     * @return
     */
    public static <T> RestResultDto<T> fail(int status, String msg, T data) {
        return new RestResultDto(status, msg, data);
    }
}
