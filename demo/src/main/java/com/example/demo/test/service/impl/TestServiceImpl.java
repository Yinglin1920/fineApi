package com.example.demo.test.service.impl;

import com.example.demo.common.PageListDto;
import com.example.demo.common.PostIdDto;
import com.example.demo.common.PostIdsDto;
import com.example.demo.common.utils.SnowflakeIdWorker;
import com.example.demo.test.dao.TestDao;
import com.example.demo.test.dao.entity.Test;
import com.example.demo.test.dto.TestDto;
import com.example.demo.test.dto.TestStatisticsDto;
import com.example.demo.test.service.TestService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/6 10:09
 * @desc
 */
@Service
public class TestServiceImpl implements TestService {


    /**
     *
     */
    @Resource
    TestDao testDao;

    /**
     *
     * @param testDto
     */
    @Override
    public void add(TestDto testDto) {
        testDao.insertData(testDto.getKind(), testDto.getType());
    }

    /**
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> get() {
        return testDao.selectData();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Test> getDto() {
        return testDao.selectDtoData();
    }

    /**
     *
     * @param postIdDto
     * @return
     * @throws Exception
     */
    @Override
    public Test getById(PostIdDto postIdDto) throws Exception {
        return testDao.getById(postIdDto.getId());
    }

    /**
     *
     * @param num
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public PageListDto<Test> getPage(Integer num, Integer size) throws Exception {
        Page page = PageHelper.startPage(num, size);
        List<Test> tests = testDao.selectDtoData();
        PageListDto<Test> testPageListDto = new PageListDto<>(page);
        return testPageListDto;
    }

    /**
     * @param testDto
     */
    @Override
    public void upsert(TestDto testDto) {
        testDao.insetUpdateData(testDto);
    }

    /**
     * @param postIdsDto
     */
    @Override
    public void deleteTest(PostIdsDto postIdsDto) {
        testDao.deleteData(postIdsDto.getIds());
    }

    /**
     * 测试统计功能
     * @return
     */
    @Override
    public List<TestStatisticsDto> getSta() {
        return testDao.getSta();
    }


}
