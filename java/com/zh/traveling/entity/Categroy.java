package com.zh.traveling.entity;

import java.io.Serializable;
//分类实体类
public class Categroy implements Serializable {
    private int cid;//分类id
    private String cname;//分类名称

    public Categroy() {
    }

    public Categroy(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Categroy{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}


