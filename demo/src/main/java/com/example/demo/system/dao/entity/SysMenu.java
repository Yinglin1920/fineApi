package com.example.demo.system.dao.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 16:36
 * @desc
 */
@Data
public class SysMenu {

    /**
     * 主键ID
     **/
    @Id
    private Long id;
    /**
     * 菜单名称
     **/
    private String name;
    /**
     * 菜单编码
     **/
    private String code;
    /**
     * 父级编码
     **/
    private String parentCode;
    /**
     * 菜单简介
     **/
    private String caption;
    /**
     * 标记
     **/
    private String tag;
    /**
     * 类名
     **/
    private String className;
    /**
     * 图标名称
     **/
    private String iconName;
    /**
     * 层级
     **/
    private Integer level;
    /**
     * 排序
     **/
    private Integer sortNum;
    /**
     * 是否需要授权
     **/
    private Boolean isAuth;

}
