package com.example.demo.common.exceptions;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/23 13:26
 * @desc
 */
public class RestException extends RuntimeException{
    /**
     * 异常代码，由业务开发划分 -1、0、1系统占用。
     */
    private int code;
    /**
     * 友好提示（可单独返回给用户），需要时可区分原始异常message（不指定则和message信息一致）
     */
    private String prompt;

    public int getCode() {
        return code;
    }

    public String getPrompt() {
        return prompt;
    }

    @Override
    public String getMessage() {
        return String.format("用户提示信息：%s，原始异常信息：%s", this.prompt, super.getMessage());
    }

    /**
     * 指定异常信息
     *
     * @param message
     */
    public RestException(String message) {
        super(message);
        this.code = 1;
        this.prompt = message;
    }

    public RestException(Exception ex) {
        super(ex);
    }

    public RestException(String message, Exception ex) {
        super(message, ex);
        this.code = 1;
        this.prompt = message;
    }

    /**
     * 指定异常code和异常信息
     *
     * @param code
     * @param message
     */
    public RestException(int code, String message) {
        super(message);
        this.code = code;
        this.prompt = message;
    }

    public RestException(int code, String message, Exception ex) {
        super(message, ex);
        this.code = code;
        this.prompt = message;
    }

    /**
     * 指定code，异常信息，友好用户提示
     *
     * @param code
     * @param message
     * @param prompt
     */
    public RestException(int code, String message, String prompt) {
        super(message);
        this.code = code;
        this.prompt = prompt;
    }

    public RestException(int code, String message, String prompt, Exception ex) {
        super(message, ex);
        this.code = code;
        this.prompt = prompt;
    }
}
