package com.zh.traveling.dao.impl;

import com.zh.traveling.dao.FavouriteDao;
import com.zh.traveling.entity.Favourite;
import com.zh.traveling.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavouriteDaoImpl implements FavouriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favourite findByRidAndUid(int rid, int uid) {
        Favourite favourite = null;
        try {
            String sql = " select * from tab_favorite where rid = ? and uid = ?";
            favourite = template.queryForObject(sql, new BeanPropertyRowMapper<Favourite>(Favourite.class), rid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favourite;
    }


    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";

        return template.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";

        template.update(sql,rid,new Date(),uid);
    }
}
