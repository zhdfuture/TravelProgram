package com.zh.traveling.service;

import com.zh.traveling.dao.CategroyDao;
import com.zh.traveling.dao.impl.CategroyDaoImpl;
import com.zh.traveling.entity.Categroy;
import com.zh.traveling.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
//旅游分类业务实现层
public class CategroyServiceImpl  implements  CategroyService{
    private CategroyDao categoryDao = new CategroyDaoImpl();
    @Override
    public List<Categroy> findAll() {
        //1.从redis中查询
        //1.1获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //查询sortedset(排序）中的分数(cid)和值(cname)
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

        List<Categroy> categroyList = null;
        //2.判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0) {

            System.out.println("从数据库查询....");
            //3.如果为空,需要从数据库查询,将数据存入redis
            categroyList = categoryDao.findAll();
            // 将集合数据存储到redis中的 category的key
            for (int i = 0; i < categroyList.size(); i++) {

                jedis.zadd("category", categroyList.get(i).getCid(), categroyList.get(i).getCname());
            }
        } else {
            System.out.println("从redis中查询.....");

            //4.如果不为空,将set的数据存入list(集合的统一）
            categroyList = new ArrayList<Categroy>();
            for (Tuple tuple : categorys) {
                Categroy category = new Categroy();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                categroyList.add(category);

            }
        }

        return categroyList;
    }
}


