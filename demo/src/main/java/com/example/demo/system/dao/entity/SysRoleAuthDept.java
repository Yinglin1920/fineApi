package com.example.demo.system.dao.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 16:41
 * @desc
 */
@Data
public class SysRoleAuthDept {
    /**
     * 主键ID
     **/
    @Id
    private Long id;
    /**
     * 角色ID
     **/
    private Integer roleId;
    /**
     * 部门code
     **/
    private String deptCode;

}
