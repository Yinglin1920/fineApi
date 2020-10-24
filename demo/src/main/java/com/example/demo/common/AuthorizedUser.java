package com.example.demo.common;

import com.example.demo.system.dao.entity.SysUser;
import lombok.Data;

import java.util.Set;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 15:21
 * @desc
 */
@Data
public class AuthorizedUser extends SysUser {

    /**
     * 用户角色
     */
    private Set<Long> roleIds;

    /**
     * 角色和用户-部门(数据)授权
     */
    private Set<String> deptCodes;

    /**
     * 角色和用户-菜单
     */
    private Set<String> menuCodes;

}
