package com.example.demo.controller;

import com.example.demo.common.RestResultDto;
import com.example.demo.system.dto.PostLoginDto;
import com.example.demo.system.service.SysService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 9:44
 * @desc
 */
@RestController
public class SystemController {

    @Resource
    SysService sysService;
    /**
     * 登录（不需要token）
     **/
    @PostMapping("/user/login")
    public RestResultDto login(@RequestBody PostLoginDto postLoginDto) throws Exception {
        return RestResultDto.success(sysService.login(postLoginDto));
    }

}
