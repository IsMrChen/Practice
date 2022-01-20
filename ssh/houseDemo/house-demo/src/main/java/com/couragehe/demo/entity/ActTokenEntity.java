package com.couragehe.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "acttoken")
public class ActTokenEntity {
    private int id;
    private Integer actid;
    private String token;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "actid")
    public Integer getActid() {
        return actid;
    }

    public void setActid(Integer actid) {
        this.actid = actid;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActTokenEntity that = (ActTokenEntity) o;

        if (id != that.id) return false;
        if (actid != null ? !actid.equals(that.actid) : that.actid != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (actid != null ? actid.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
