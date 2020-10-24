package com.example.demo.common;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/7 9:06
 * @desc
 */
public interface BaseMapper<T> extends tk.mybatis.mapper.common.Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
}
