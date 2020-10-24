package com.example.demo.common;

import lombok.Data;

import java.util.List;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/7 11:58
 * @desc
 */
@Data
public class PostIdsDto {

    /**
     * 需要操作的主键ID列表
     */
    private List<Long> ids;
}
