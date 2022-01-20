package com.couragehe.demo.dao.impl;

import com.couragehe.demo.dao.ActDAOI;
import com.couragehe.demo.entity.ActEntity;
import com.couragehe.demo.tools.ResponseData;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @program: house-demo
 * @description: 用户的实现类
 * @author: dandan.chen
 * @date 2020-10-27 23:32:54
 */
@Repository
@Transactional
public class ActDAOImpl implements ActDAOI {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ActEntity save(ActEntity actEntity) {
        sessionFactory.getCurrentSession().save(actEntity);
        return actEntity;
    }

    @Override
    public ActEntity findAct(ActEntity actEntity) {
        ResponseData responseData=null;
        String hql="from ActEntity act where act.username='"+actEntity.getUsername()+"'"+" and act.password='"+actEntity.getPassword()+"'";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        List<ActEntity> actEntityList=query.list();
        if(actEntityList.size()>0){
            return actEntityList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<ActEntity> findAll() {
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(ActEntity.class);
        List<ActEntity> actEntities=criteria.list();
        return actEntities;
    }

}
