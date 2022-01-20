package com.cdd.dao;

import com.cdd.domain.Account;

import java.util.List;

/**
 * @Author: Dandan.Chen
 * @Description:
 * @Date:Created in 12:04 2020/10/15
 * @Modified By:
 */
public interface IUserDao {
    List<Account> findAll();
    void in(String inMan,Integer money);
    void out(String outMan,Integer money);
}
