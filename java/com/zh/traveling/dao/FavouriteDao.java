package com.zh.traveling.dao;

import com.zh.traveling.entity.Favourite;

public interface FavouriteDao {


     //根据rid和uid查询收藏信息
    public Favourite findByRidAndUid(int rid, int uid);


     //根据rid 查询收藏次数

    public int findCountByRid(int rid);


     // 添加收藏

    void add(int i, int uid);
}


