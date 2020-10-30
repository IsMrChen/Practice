package com.couragehe.demo.filter;

import com.alibaba.fastjson.JSON;
import com.couragehe.demo.tools.ResponseData;
import com.couragehe.demo.tools.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: house-demo
 * @description: 登录请求验证token
 * @author: dandan.chen
 * @date 2020-10-28 11:28:10
 */
public class HeaderTokenInterceptor implements HandlerInterceptor {
    private final static Logger logger= LoggerFactory.getLogger(HeaderTokenInterceptor.class);
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        logger.info("filter running....");
        ResponseData responseData=null;
        //获取我们请求头中的token验证字符
        String headerToken=request.getHeader("token");
        //检测当前页面，我们设置当前页面不是登录注册页面时，对其进行拦截
        //检测url中有没有login字符
        System.out.println("request url  "+request.getRequestURI());
        if(!request.getRequestURI().contains("act")){
            if(headerToken==null) {
                //如果token不存在的话，返回错误信息
                responseData = ResponseData.customerError();
                logger.info("token不存在");
            }
            try {
                // 对token进行更新与验证
                TokenUtil.verify(headerToken);
            } catch (Exception e) {
                // 当token验证出现异常返回错误信息,token不匹配
                responseData=ResponseData.customerError();
            }
        }
        if(responseData!=null) {//如果有错误信息
            response.getWriter().write(JSON.toJSONString(responseData));
            return false;
        }else {
            // 将token加入返回页面的header
            response.setHeader("token", headerToken);
            return true;
        }
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
