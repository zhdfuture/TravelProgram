package com.zh.traveling.dao;

import com.zh.traveling.entity.RouteImg;

import java.util.List;

public interface RouteImgDao {


     // 根据route的id查询图片

    public List<RouteImg> findByRid(int rid);
}
