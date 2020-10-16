package com.cdd.domain;

/**
 * @Author: Dandan.Chen
 * @Description:
 * @Date:Created in 12:02 2020/10/15
 * @Modified By:
 */
public class Account {
    private String name;
    private Integer money;

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
