package com.oven.multitenant;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据源配置dao层
 *
 * @author Oven
 */
@Repository
public class DataSourceConfigDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<DataSourceConfig> findAll() {
        String sql = "select * from t_datasource_config";
        return this.jdbcTemplate.query(sql, (rs, rowNum) -> {
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setId(rs.getLong("id"));
            dataSourceConfig.setName(rs.getString("_name"));
            dataSourceConfig.setPassword(rs.getString("_password"));
            dataSourceConfig.setUsername(rs.getString("username"));
            dataSourceConfig.setUrl(rs.getString("url"));
            dataSourceConfig.setDriverClassName(rs.getString("driverclassname"));
            return dataSourceConfig;
        });
    }

    public DataSourceConfig findByName(String name) {
        String sql = "select * from t_datasource_config where _name = ?";
        List<DataSourceConfig> list = this.jdbcTemplate.query(sql, (rs, rowNum) -> {
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setId(rs.getLong("id"));
            dataSourceConfig.setPassword(rs.getString("_password"));
            dataSourceConfig.setName(rs.getString("_name"));
            dataSourceConfig.setUsername(rs.getString("username"));
            dataSourceConfig.setUrl(rs.getString("url"));
            dataSourceConfig.setDriverClassName(rs.getString("driverclassname"));
            return dataSourceConfig;
        }, name);
        return list.size() == 0 ? null : list.get(0);
    }

}
