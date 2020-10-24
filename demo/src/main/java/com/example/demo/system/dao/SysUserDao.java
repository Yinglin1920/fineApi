package com.example.demo.system.dao;

import com.example.demo.common.BaseMapper;
import com.example.demo.system.dao.entity.*;
import com.example.demo.system.dto.SysUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 14:46
 * @desc
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 通过登录名称查询用户
     * @param name
     * @return
     */
    SysUserDto getUserDtoByName(@Param("name") String name);

    /**
     * 通过uid查询用户实体
     * @param uid
     * @return
     */
    SysUserDto getUserDtoByUID(@Param("uid") Long uid);

    /**
     * 通过uid来查询密码
     * @param userId
     * @return
     */
    SysUserPassword getPasswordByUID(@Param("userId") Long userId);

    /**
     * 获取用户的角色列表
     * @param uid
     * @return
     */
    List<SysUserRole> getUserRolesByUID(@Param("uid") Long uid);

    /**
     * 获取角色的部门权限
     * @param uid
     * @return
     */
    List<SysRoleAuthDept> getRoleAuthDeptByUID(@Param("uid") Long uid);

    /**
     * 获取用户拥有的角色的菜单权限
     * @param uid
     * @return
     */
    List<SysRoleAuthMenu> getRoleAuthMenuByUID(@Param("uid") Long uid);

    /**
     * 获取用户的所在部门的部门权限
     * @param deptCode
     * @return
     */
    List<SysOrg> getUserNatureDept(@Param("deptCode") String deptCode );
}
