package com.example.demo.system.dao.entity;

import com.example.demo.common.CommonPart;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 14:38
 * @desc
 */
@Data
public class SysUser extends CommonPart {
    /**
     * 主键ID
     **/
    @Id
    private Long id;
    /**
     * 登录名称
     **/
    private String loginName;
    /**
     * 用户名称
     **/
    private String userName;
    /**
     * 电话号码
     **/
    private String mobile;
    /**
     * 职务
     **/
    private String job;
    /**
     * 是否启动
     **/
    private Boolean isAbled;

}
