package com.example.demo.system.service;

import com.example.demo.common.AuthorizedUser;
import com.example.demo.system.dto.PostLoginDto;
import com.example.demo.system.dto.SysLoginDto;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 14:41
 * @desc
 */
public interface SysService {

    /**
     * 登录
     *
     * @param postLoginDto
     * @return
     */
    SysLoginDto login(PostLoginDto postLoginDto);

    /**
     * 查询授权用户
     *
     * @param userId
     * @return
     */
    AuthorizedUser getAuthUserByUserId(Long userId);
}
