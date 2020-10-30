package com.couragehe.demo.dto;

import com.couragehe.demo.entity.ActEntity;

/**
 * @program: house-demo
 * @description:
 * @author: dandan.chen
 * @date 2020-10-28 10:48:00
 */
public class ActDto {
    int actId;//用户id
    String username;//用户名
    String token;//当前token

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
