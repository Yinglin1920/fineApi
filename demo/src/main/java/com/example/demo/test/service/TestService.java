package com.example.demo.test.service;

import com.example.demo.common.PageListDto;
import com.example.demo.common.PostIdDto;
import com.example.demo.common.PostIdsDto;
import com.example.demo.test.dao.entity.Test;
import com.example.demo.test.dto.TestDto;
import com.example.demo.test.dto.TestStatisticsDto;

import java.util.List;
import java.util.Map;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/6 10:09
 * @desc
 */
public interface TestService {

    void add(TestDto testDto);

    List<Map<String, Object>> get();

    List<Test> getDto() throws Exception;

    Test getById(PostIdDto postIdDto) throws Exception;

    PageListDto<Test> getPage(Integer page, Integer size) throws Exception;

    void upsert(TestDto testDto) throws Exception;

    void deleteTest(PostIdsDto postIdsDto) throws Exception;

    List<TestStatisticsDto> getSta() throws Exception;

}
