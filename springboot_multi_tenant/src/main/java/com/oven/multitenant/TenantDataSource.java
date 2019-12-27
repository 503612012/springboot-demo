package com.oven.multitenant;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据租户生成数据源
 *
 * @author Oven
 */
@Component
public class TenantDataSource implements Serializable {

    private HashMap<String, DataSource> dataSources = new HashMap<>();

    @Resource
    private DataSourceConfigDao dataSourceConfigDao;

    /**
     * 初始化所有租户数据源
     */
    public Map<String, DataSource> getAll() {
        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigDao.findAll(); // 获取所有租户数据源配置
        Map<String, DataSource> result = new HashMap<>();
        for (DataSourceConfig config : dataSourceConfigList) {
            DataSource dataSource = getDataSource(config.getName()); // 获取数据源
            result.put(config.getName(), dataSource);
        }
        return result;
    }

    /**
     * 根据租户名称获取数据源
     */
    public DataSource getDataSource(String name) {
        if (dataSources.get(name) != null) {
            return dataSources.get(name);
        }
        DataSource dataSource = createDataSource(name);
        if (dataSource != null) {
            dataSources.put(name, dataSource);
        }
        return dataSource;
    }

    /**
     * 创建数据源
     *
     * @param name 租户名称
     */
    private DataSource createDataSource(String name) {
//        DataSourceConfig config = dataSourceConfigDao.findByName(name);
//        if (config != null) {
//            DriverManagerDataSource dataSource = new DriverManagerDataSource();
//            dataSource.setDriverClassName(config.getDriverClassName());
//            dataSource.setUsername(config.getUsername());
//            dataSource.setPassword(config.getPassword());
//            dataSource.setUrl(config.getUrl());
//            return dataSource;
//        }
//        return null;

        // 改成driud后，租户数据源配置表中应该增加相应的配置信息 TODO
        DataSourceConfig config = dataSourceConfigDao.findByName(name);
        if (config != null) {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(config.getDriverClassName());
            dataSource.setUsername(config.getUsername());
            dataSource.setPassword(config.getPassword());
            dataSource.setUrl(config.getUrl());
            return dataSource;
        }
        return null;
    }

}
