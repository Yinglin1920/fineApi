package com.example.demo.controller;

import com.example.demo.common.PageParam;
import com.example.demo.common.PostIdDto;
import com.example.demo.common.PostIdsDto;
import com.example.demo.common.RestResultDto;
import com.example.demo.test.dao.entity.Test;
import com.example.demo.test.dto.TestDto;
import com.example.demo.test.service.TestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/5 23:20
 * @desc
 */
@RestController
public class TestController {


    @Resource
    TestService testService;

//    /**
//     * 测试
//     * @return
//     */
//    @RequestMapping("/test")
//    public String index(){
//        return "OK";
//    }

    /**
     * 测试
     * @throws Exception
     */
    @PostMapping("/test")
    public RestResultDto test() throws Exception {
        return RestResultDto.success("OK");
    }

    /**
     * 新增
     * @throws Exception
     */
    @PostMapping("/add")
    public RestResultDto add(@RequestBody TestDto testDto) throws Exception {
        testService.add(testDto);
        return RestResultDto.success("OK");
    }

    /**
     * 新增或修改
     * @throws Exception
     */
    @PostMapping("/upsert")
    public RestResultDto upsert(@RequestBody TestDto testDto) throws Exception {
        testService.upsert(testDto);
        return RestResultDto.success("OK");
    }

    /**
     * 删除
     * @param postIdsDto
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public RestResultDto delete(@RequestBody PostIdsDto postIdsDto)throws Exception {
        testService.deleteTest(postIdsDto);
        return RestResultDto.success("OK");
    }

    /**
     * 查询
     * @return
     * @throws Exception
     */
    @PostMapping("/get")
    public RestResultDto get() throws Exception {
        return RestResultDto.success(testService.get());
    }

    /**
     * 通过ID查询
     * @return
     * @throws Exception
     */
    @PostMapping("/getById")
    public RestResultDto getById(@RequestBody PostIdDto postIdDto) throws Exception {
        return RestResultDto.success(testService.getById(postIdDto));
    }

    /**
     * 查询
     * @return
     * @throws Exception
     */
    @PostMapping("/getDto")
    public RestResultDto getDto() throws Exception {
        return RestResultDto.success(testService.getDto());
    }

    /**
     * 分页查询
     * @param pageParam
     * @return
     * @throws Exception
     */
    @PostMapping("/getPage")
    public RestResultDto getPage(@RequestBody PageParam pageParam) throws Exception {
        return RestResultDto.success(testService.getPage(pageParam.getPageNo(),pageParam.getPageSize()));
    }

    /**
     * 统计表格
     * @return
     * @throws Exception
     */
    @PostMapping("/getSta")
    public RestResultDto getSta() throws Exception {
        return RestResultDto.success(testService.getSta());
    }
}
