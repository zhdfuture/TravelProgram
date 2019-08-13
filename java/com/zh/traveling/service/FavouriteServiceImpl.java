package com.zh.traveling.service;

import com.zh.traveling.dao.FavouriteDao;
import com.zh.traveling.dao.impl.FavouriteDaoImpl;
import com.zh.traveling.entity.Favourite;

public class FavouriteServiceImpl implements FavouriteService {
    private FavouriteDao favoriteDao = new FavouriteDaoImpl();
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favourite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        return favorite != null;//如果对象有值，则为true，反之，则为false
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }
}
