package com.zh.traveling.dao.impl;

import com.zh.traveling.dao.CategroyDao;
import com.zh.traveling.entity.Categroy;
import com.zh.traveling.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategroyDaoImpl implements CategroyDao {
    //声明模板对象，执行查询
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Categroy> findAll() {
        String sql = "select * from tab_category ";
        return template.query(sql,new BeanPropertyRowMapper<Categroy>(Categroy.class));
    }
}
