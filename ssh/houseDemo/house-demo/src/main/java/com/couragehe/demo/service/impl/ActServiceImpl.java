package com.couragehe.demo.service.impl;

import com.couragehe.demo.dao.ActDAOI;
import com.couragehe.demo.dao.ActTokenDAOI;
import com.couragehe.demo.entity.ActEntity;
import com.couragehe.demo.entity.ActTokenEntity;
import com.couragehe.demo.service.ActServiceI;
import com.couragehe.demo.tools.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @program: house-demo
 * @description:
 * @author: dandan.chen
 * @date 2020-10-27 23:53:54
 */
@Service
@Transactional
public class ActServiceImpl implements ActServiceI {
    @Autowired
    ActDAOI actDAOI;
    @Autowired
    ActTokenDAOI actTokenDAOI;
    @Override
    public List<ActEntity> findAll() {
        return actDAOI.findAll();
    }

    @Override
    public ActEntity register(ActEntity actEntity) {
        if(actDAOI.findAct(actEntity)!=null){
            return null;
        }else {
            actDAOI.save(actEntity);
        }
        return actDAOI.findAct(actEntity);
    }

    @Override
    public ActEntity login(ActEntity actEntity) {
        ActEntity act =actDAOI.findAct(actEntity);
        ActTokenEntity actTokenEntity=new ActTokenEntity();
        if(act!=null){
            Integer tokenId=actTokenDAOI.getTonkenId(act.getId());
            if(tokenId!=null){
                actTokenEntity.setId(tokenId);
            }
            actTokenEntity.setActid(act.getId());
            actTokenEntity.setToken(TokenUtil.token(act.getUsername(),act.getPassword()));
            actTokenDAOI.updateToken(actTokenEntity);
            return act;
        }
        return null;
    }

    @Override
    public String getToken(ActEntity actEntity) {
        return actTokenDAOI.getTokenByActId(actEntity.getId());
    }
}
