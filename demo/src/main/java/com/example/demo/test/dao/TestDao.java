package com.example.demo.test.dao;

import com.example.demo.common.BaseMapper;
import com.example.demo.test.dao.entity.Test;
import com.example.demo.test.dto.TestDto;
import com.example.demo.test.dto.TestStatisticsDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/6 9:34
 * @desc
 */
@Mapper
public interface TestDao extends BaseMapper<Test> {

    /**
     *
     * @param kind
     * @param type
     */
    @Insert("insert into lyl_test (kind,type) values (#{kind},#{type})")
    void insertData(@Param("kind") String kind,
                    @Param("type") String type);

    /**
     *
     * @return
     */
    List<Map<String,Object>> selectData();

    /**
     *
     * @return
     */
    List<Test> selectDtoData();

    /**
     *
     * @return
     */
    Test getById(@Param("id") Long id);

    /**
     *
     * @param testDto
     */
    void insetUpdateData(TestDto testDto);

    /**
     *
     * @param ids
     */
    void deleteData(@Param("ids") List<Long> ids);

    /**
     *
     * @return
     */
    List<TestStatisticsDto> getSta();
}
