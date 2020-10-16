package com.cdd.dao.impl;

import com.cdd.dao.IUserDao;
import com.cdd.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Dandan.Chen
 * @Description:
 * @Date:Created in 12:04 2020/10/15
 * @Modified By:
 */
@Repository("userDao")
public class UserDaoImpl implements IUserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Account> findAll() {
        return jdbcTemplate.query("select * from account",new BeanPropertyRowMapper<Account>(Account.class));
    }
    public void in(String inMan,Integer money){
        jdbcTemplate.update("update account set money=money+? where name=?",money,inMan);
    }
    public void out(String outMan,Integer money){
        jdbcTemplate.update("update account set money=money-? where name=?",money,outMan);
    }
}
