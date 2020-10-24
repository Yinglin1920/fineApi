package com.example.demo.system.dao.entity;

import com.example.demo.common.CommonPart;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 16:35
 * @desc
 */
@Data
public class SysRole extends CommonPart {
    /**
     * 主键ID
     **/
    @Id
    private Long id;
    /**
     * 角色名称
     **/
    private String roleName;
    /**
     * 是否通用
     **/
    private Boolean isCommon;

}
