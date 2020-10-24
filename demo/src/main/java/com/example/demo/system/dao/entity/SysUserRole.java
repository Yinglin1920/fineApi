package com.example.demo.system.dao.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 16:40
 * @desc
 */
@Data
public class SysUserRole {
    /**
     * 主键ID
     **/
    @Id
    private Long id;
    /**
     * 用户ID
     **/
    private Long userId;
    /**
     * 角色ID
     **/
    private Long roleId;


}
