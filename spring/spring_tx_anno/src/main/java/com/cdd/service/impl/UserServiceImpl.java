package com.cdd.service.impl;


import com.cdd.dao.IUserDao;
import com.cdd.domain.Account;
import com.cdd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Dandan.Chen
 * @Description:
 * @Date:Created in 13:00 2020/10/15
 * @Modified By:
 */
@Service("userService")
@Transactional(isolation = Isolation.READ_COMMITTED)
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public List<Account> findAll() {
        return userDao.findAll();
    }
    @Override
    @Transactional//进行事务控制
    public void transfer(String outMan, String inMan, Integer money) {
        userDao.out(outMan,money);
        //int i=1/0;
        userDao.in(inMan,money);
    }
}
