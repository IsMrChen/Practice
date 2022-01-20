package com.cdd.service.impl;

import com.cdd.dao.IUserDao;
import com.cdd.dao.impl.UserDaoImpl;
import com.cdd.domain.Account;
import com.cdd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Dandan.Chen
 * @Description:
 * @Date:Created in 13:00 2020/10/15
 * @Modified By:
 */
public class UserServiceImpl implements IUserService {
    private IUserDao userDao;
    @Override
    public List<Account> findAll() {
        return userDao.findAll();
    }
    @Override
    public void transfer(String outMan, String inMan, Integer money) {
        userDao.out(outMan,money);
        //测试中断
		//int i=1/0;
        userDao.in(inMan,money);
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

}
