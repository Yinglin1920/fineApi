package com.example.demo.system.service.impl;

import com.example.demo.common.AuthorizedUser;
import com.example.demo.common.Jwt;
import com.example.demo.common.ObjectHelper;
import com.example.demo.common.exceptions.RestException;
import com.example.demo.common.utils.DESUtils;
import com.example.demo.common.utils.PasswordUtils;
import com.example.demo.common.utils.StringUtils;
import com.example.demo.system.consts.LoginStatusEnum;
import com.example.demo.system.dao.SysUserDao;
import com.example.demo.system.dao.entity.*;
import com.example.demo.system.dto.PostLoginDto;
import com.example.demo.system.dto.SysLoginDto;
import com.example.demo.system.dto.SysUserDto;
import com.example.demo.system.service.SysService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 14:48
 * @desc
 */
@Service
public class SysServiceImpl implements SysService {

    @Resource
    private SysUserDao sysUserDao;

    /**
     *
     * @param postLoginDto
     * @return
     */
    @Override
    public SysLoginDto login(PostLoginDto postLoginDto) {

        // 得到解密后password
        String password = DESUtils.decrypt(postLoginDto.getPassword());

        if (StringUtils.isEmpty(postLoginDto.getUsername()) || StringUtils.isEmpty(password)) {
            throw new RestException(LoginStatusEnum.EMPTY_INPUT.getMsg());
        }

        SysUserDto sysUserDto = sysUserDao.getUserDtoByName(postLoginDto.getUsername());
        if (ObjectHelper.isEmpty(sysUserDto)) {
            throw new RuntimeException(LoginStatusEnum.INVALID_USER.getMsg());
        } else if (sysUserDto.getIsAbled().equals(false)) {
            throw new RestException(LoginStatusEnum.NOT_ABLE.getMsg());
        }

        SysUserPassword sysUserPassword = sysUserDao.getPasswordByUID(sysUserDto.getId());
        if (ObjectHelper.isEmpty(sysUserPassword)) {
            throw new  RestException(LoginStatusEnum.INVALID_PWD.getMsg());
        }

        String cipher = PasswordUtils.cipher(password, sysUserPassword.getCipherSalt());
        if (!sysUserPassword.getCipherCode().equals(cipher)) {
            throw new  RestException(LoginStatusEnum.INVALID_PWD.getMsg());
        }

        AuthorizedUser user = getAuthUserByUserId(sysUserDto.getId());
        return new SysLoginDto(
                Jwt.getToken(sysUserDto.getId().toString(), user),
                sysUserDto.getId(),
                LoginStatusEnum.LOGIN_SUCCESS.getMsg()
        );
    }

    /**
     * 获取当前用户的权限
     * @param uid
     * @return
     */
    @Override
    public AuthorizedUser getAuthUserByUserId(Long uid) {
        SysUserDto sysUser = sysUserDao.getUserDtoByUID(uid);
        if (ObjectHelper.isEmpty(sysUser)) {
            return null;
        }
        List<SysUserRole> sysUserRoles = sysUserDao.getUserRolesByUID(uid);

        List<SysRoleAuthDept> sysRoleAuthDepts = sysUserDao.getRoleAuthDeptByUID(uid);
        List<SysRoleAuthMenu> sysRoleAuthMenus = sysUserDao.getRoleAuthMenuByUID(uid);



        // 设置授权用户
        AuthorizedUser authorizedUser = new AuthorizedUser();
        authorizedUser.setId(sysUser.getId());
        authorizedUser.setLoginName(sysUser.getLoginName());
        authorizedUser.setUserName(sysUser.getUserName());
        authorizedUser.setMobile(sysUser.getMobile());
        authorizedUser.setJob(sysUser.getJob());
        authorizedUser.setDeptName(sysUser.getDeptName());
        authorizedUser.setDeptCode(sysUser.getDeptCode());


        Set<Long> roleSet = sysUserRoles.stream().map(s -> s.getRoleId()).collect(Collectors.toSet());
        authorizedUser.setRoleIds(roleSet);



        // 设置授权用户的数据权限信息
        Set<String> authDepts = new HashSet<>();
        authDepts.addAll(

                sysRoleAuthDepts.stream().filter(Objects::nonNull).map(s -> s.getDeptCode()).collect(Collectors.toSet())
        );
        List<SysOrg> userDept = sysUserDao.getUserNatureDept(sysUser.getDeptCode());
        if (ObjectHelper.isNotEmpty(userDept)) {
            Set<String> codes = userDept.stream().filter(Objects::nonNull).map(s -> s.getCode()).collect(Collectors.toSet());
            authDepts.addAll(codes);

        }
        authorizedUser.setDeptCodes(authDepts);

        // 设置授权用户的菜单权限
        Set<String> authMenus = new HashSet<>();
        authMenus.addAll(
                sysRoleAuthMenus.stream().filter(Objects::nonNull).map(s -> s.getMenuCode()).collect(Collectors.toSet())
        );
        authorizedUser.setMenuCodes(authMenus);

        return authorizedUser;
    }
}
