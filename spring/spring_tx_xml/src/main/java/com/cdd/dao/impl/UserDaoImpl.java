package com.cdd.dao.impl;

import com.cdd.dao.IUserDao;
import com.cdd.domain.Account;
import jdk.nashorn.internal.objects.annotations.Property;
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
public class UserDaoImpl implements IUserDao {
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

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
