package com.zh.traveling.dao.impl;

import com.zh.traveling.dao.RouteDao;
import com.zh.traveling.entity.Route;
import com.zh.traveling.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid, String rname) {
        //定义sql模板
        String sql = "select count(*) from tab_route where 1=1 ?";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件
        //2.判断参数是否有值
        if(cid != 0){
            sb.append( " and cid = ? ");

            params.add(cid);//添加？对应的值
        }

        if(rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");   //模糊查询

            params.add("%"+rname+"%");
        }

        sql = sb.toString();


        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
       // String sql="select * from tab_route where cid=? limit ?,?";
        String sql = " select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(cid != 0){
            sb.append( " and cid = ? ");

            params.add(cid);//添加？对应的值
        }

        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }


    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
