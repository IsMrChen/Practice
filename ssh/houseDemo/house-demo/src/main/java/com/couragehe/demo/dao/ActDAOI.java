package com.couragehe.demo.dao;

import com.couragehe.demo.entity.ActEntity;

import java.util.List;

/**
 * @program: house-demo
 * @description: 用户dao
 * @author: dandan.chen
 * @date 2020-10-27 23:23:08
 */
public interface ActDAOI {
    ActEntity save(ActEntity actEntity);
    ActEntity findAct(ActEntity actEntity);
    List<ActEntity> findAll();
}
