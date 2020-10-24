package com.example.demo.test.dao.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/7 10:21
 * @desc
 */
@Data
public class Test {

    @Id
    private Integer id;

    private String kind;
    private String type;


}
