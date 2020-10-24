package com.example.demo.common;

import lombok.Data;

import java.util.Date;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/7 11:50
 * @desc
 */
@Data
public class PageParam {

    /**
     * 页码
     */
    private Integer pageNo=1;

    /**
     * 页码大小
     */
    private Integer pageSize=10;

    /**
     * 查询开始时间
     */
    private Date startTime;

    /**
     * 查询结束时间
     */
    private Date endTime;


    /**
     * 默认(1=倒序，0=正序)
     * add by lyl
     * 控制分页列表的正序或者倒序
     */
    private Integer sortType=1;

}
