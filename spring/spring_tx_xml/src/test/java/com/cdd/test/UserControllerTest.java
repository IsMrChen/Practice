package com.cdd.test;

import com.cdd.service.IUserService;
import com.cdd.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: Dandan.Chen
 * @Description:
 * @Date:Created in 13:47 2020/10/15
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserControllerTest {
    @Autowired
    private IUserService userService;
    @Test
    public void test01(){
        userService.transfer("A","B",500);
        System.out.println( userService.findAll());
    }

}
