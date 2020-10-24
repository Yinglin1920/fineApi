package com.example.demo.system.dto;

import lombok.Data;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 14:44
 * @desc
 */
@Data
public class SysLoginDto {
    /**
     * token
     */
    private String Token;

    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private String msg;

    public SysLoginDto(String token, Long userId, String msg) {
        Token = token;
        this.userId = userId;
        this.msg = msg;
    }
}
