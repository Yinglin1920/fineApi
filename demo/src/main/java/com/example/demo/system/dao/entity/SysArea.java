package com.example.demo.system.dao.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 16:39
 * @desc
 */
@Data
public class SysArea {
    /**
     * 主键ID
     **/
    @Id
    private Long id;
    /**
     * 编号
     **/
    private String code;
    /**
     * 数据值
     **/
    private String val;
    /**
     * 父级编号
     **/
    private String parentCode;

}
