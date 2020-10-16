package com.cdd.service;


import com.cdd.domain.Account;

import java.util.List;

/**
 * @Author: Dandan.Chen
 * @Description:
 * @Date:Created in 12:59 2020/10/15
 * @Modified By:
 */
public interface IUserService {
    List<Account> findAll();
    void transfer(String outMan,String inMan,Integer money);
}
