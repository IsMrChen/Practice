package com.couragehe.demo.controller;

import com.couragehe.demo.dto.ActDto;
import com.couragehe.demo.entity.ActEntity;
import com.couragehe.demo.service.ActServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: house-demo
 * @description:
 * @author: dandan.chen
 * @date 2020-10-27 23:48:10
 */
@Controller
@RequestMapping("/act")
public class ActController {
    @Autowired
    ActServiceI actServiceI;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public List<ActEntity> findAct(){
        List<ActEntity> actEntityList=actServiceI.findAll();
        return actEntityList;

    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public ActEntity register(@RequestBody ActEntity actEntity){
        return  actServiceI.register(actEntity);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ActDto login(@RequestBody ActEntity actEntity){
        ActDto actDto=new ActDto();
        ActEntity act =actServiceI.login(actEntity);
        actDto.setActId(act.getId());
        actDto.setUsername(act.getUsername());
        actDto.setToken(actServiceI.getToken(act));
        return  actDto;
    }

}
