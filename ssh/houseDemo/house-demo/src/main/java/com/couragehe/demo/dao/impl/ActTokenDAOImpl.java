package com.couragehe.demo.dao.impl;

import com.couragehe.demo.dao.ActTokenDAOI;
import com.couragehe.demo.entity.ActTokenEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @program: house-demo
 * @description:
 * @author: dandan.chen
 * @date 2020-10-28 09:10:32
 */
@Repository
public class ActTokenDAOImpl implements ActTokenDAOI {

    @Autowired
    SessionFactory sessionFactory;
    @Override
    public String getTokenByActId(Integer actId) {
        String hql="from ActTokenEntity where actid="+actId;
        Query query= sessionFactory.getCurrentSession().createQuery(hql);
        List<ActTokenEntity> actTokenEntities=query.list();
        if(actTokenEntities.size()>0){
            return actTokenEntities.get(0).getToken();
        }
        return null;
    }

    @Override
    public Boolean updateToken(ActTokenEntity actTokenEntity) {
        String hql="";
        if(getTonkenId(actTokenEntity.getActid())!=null){
            hql="update ActTokenEntity t set t.token='"+actTokenEntity.getToken()+"'"+" where actid="+actTokenEntity.getId();
            Query query=sessionFactory.getCurrentSession().createQuery(hql);
        }else{
            sessionFactory.getCurrentSession().save(actTokenEntity);
        }

        return true;
    }

    @Override
    public Integer getTonkenId(Integer actId) {
        String hql="from ActTokenEntity where actid="+actId;
        Query query= sessionFactory.getCurrentSession().createQuery(hql);
        List<ActTokenEntity> actTokenEntities=query.list();
        if(actTokenEntities.size()>0){
            return actTokenEntities.get(0).getId();
        }
        return null;
    }

}
