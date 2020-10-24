package com.example.demo.common;

import lombok.Data;

import java.util.Date;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 14:36
 * @desc
 */
@Data
public class CommonPart {
    /**
     * 所属部门编码【等同当前操作者所在部门编码】
     **/
    private String deptCode;
    /**
     * 所属部门名称【等同当前操作者所在部门名称】
     **/
    private String deptName;
    /**
     * 创建人标识
     **/
    private Long createdBy;
    /**
     * 创建人名称（登录名称）
     **/
    private String createdName;
    /**
     * 创建日期
     **/
    private Date createdDate;
    /**
     * 最近一次修改人标识
     **/
    private Long lastModifiedBy;
    /**
     * 最近一次修改人名称
     **/
    private String lastModifiedName;
    /**
     * 最近一次修改时间
     **/
    private Date lastModifiedDate;

}
