package com.couragehe.demo.dao;

import com.couragehe.demo.entity.ActTokenEntity;

/**
 * @program: house-demo
 * @description:
 * @author: dandan.chen
 * @date 2020-10-28 09:10:11
 */
public interface ActTokenDAOI {
    String getTokenByActId(Integer actId);
    Boolean updateToken(ActTokenEntity actTokenEntity);
    Integer getTonkenId(Integer actId);
}
