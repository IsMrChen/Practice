package com.couragehe.demo.common.vo;

/**
 * @program: house-demo
 * @description:
 * @author: dandan.chen
 * @date 2020-10-27 23:34:30
 */
public enum Code {
    /*********************STA_SYS************/
    SUCCESS("0","成功"),
    ERROR("1","失败"),

    Exit_ACCOUNT("10000","该账号已被占用!"),
    Exit_MOBILE("10001","该手机号已被占用!"),
    Exit_EMAIL("10002","该邮箱已被占用!"),
    NOT_Exit_EMAIL("10003","该邮箱尚未注册!"),
    NOT_Band_EMAIL("10004","该账号暂未绑定邮箱!"),
    HAVE_Band_EMAIL("10005","该账号已绑定邮箱!"),
    NOT_EXIT_USER("10006","该账号不存在!"),
    ERR_verifyVcode("10007","验证码错误或过期！"),
    incomplete_Account("10008","账号信息缺省！"),

    SUGGESTION_HAVE_REPLY("20000","该意见反馈已经回复!"),

    NOT_SUPPORT_TYPE("30000","暂不支持该类型!"),

    CANT_NOT_ADDFRIEND_SELF("50000","不能向自己发出好友申请!"),
    HAVE_BEENFRIEND("50001","该用户已经是你好友!"),

    NO_WTGROUP_RIGHT("60000","权限不足!"),
    NO_SUPPORT_WTGROUP("60001","不支持该操作!"),


    NOT_PARAM("11001","缺少必要参数！"),
    ERR_PWD("11002","密码错误！"),
    ERR_PWD_OR_ACCOUNT("11003","账号或密码错误！"),
    Exit_Mobile("11004","该手机号已被占用!"),

    MobileHasBand("11007","该手机号已被绑定使用！"),
    NOT_ACCOUNT("11008","账号不存在！"),
    ERR_TOKEN("11009","验证密钥过期，请重新登陆"),
    ERR_MOBILE("11010","错误的手机号码"),
    NOT_LOGIN("11011","未登录"),
    UNKNOWCLASS("11012","未知数据类型"),

    NO_REGISTERED_MOBILE("11014","该手机号尚未注册!"),
    REGISTERED_MOBILE("11015","该手机号已注册!"),
    PROHIBITION("11016","该账号已被禁用!"),
    ;
    /*********************END_SYS************/
    private String code ;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private Code(String code,String msg){
        this.setCode(code);
        this.setMsg(msg);
    }

    @Override
    public String toString(){
        return code;
    }
}
