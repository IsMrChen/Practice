package com.couragehe.demo.service;

import com.couragehe.demo.entity.ActEntity;

import java.util.List;

/**
 * @program: house-demo
 * @description:
 * @author: dandan.chen
 * @date 2020-10-27 23:53:38
 */

public interface ActServiceI {
    List<ActEntity> findAll();
    ActEntity register(ActEntity actEntity);
    ActEntity login(ActEntity actEntity);
    String getToken(ActEntity actEntity);
}
