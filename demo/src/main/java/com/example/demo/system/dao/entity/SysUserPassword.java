package com.example.demo.system.dao.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 14:39
 * @desc
 */
@Data
public class SysUserPassword {
    /**
     * 主键ID
     **/
    @Id
    private Long id;
    /**
     * 用户ID
     **/
    private Integer userId;
    /**
     * 类型
     **/
    private String cipherType;
    /**
     * 盐值
     **/
    private String cipherSalt;
    /**
     * 编码
     **/
    private String cipherCode;

}
