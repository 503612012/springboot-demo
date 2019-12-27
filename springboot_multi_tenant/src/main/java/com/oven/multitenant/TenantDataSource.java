package com.oven.multitenant;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TenantDataSource implements Serializable {

    private HashMap<String, DataSource> dataSources = new HashMap<>();

    @Resource
    private DataSourceConfigDao dataSourceConfigDao;

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

    public Map<String, DataSource> getAll() {
        List<DataSourceConfig> configList = dataSourceConfigDao.findAll();
        Map<String, DataSource> result = new HashMap<>();
        for (DataSourceConfig config : configList) {
            DataSource dataSource = getDataSource(config.getName());
            result.put(config.getName(), dataSource);
        }
        return result;
    }

    private DataSource createDataSource(String name) {
        DataSourceConfig config = dataSourceConfigDao.findByName(name);
        if (config != null) {
//            DataSourceBuilder factory = DataSourceBuilder
//                    .create().driverClassName(config.getDriverClassName())
//                    .username(config.getUsername())
//                    .password(config.getPassword())
//                    .url(config.getUrl());
//            DataSource ds = factory.build();
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(config.getDriverClassName());
            dataSource.setUsername(config.getUsername());
            dataSource.setPassword(config.getPassword());
            dataSource.setUrl(config.getUrl());
            if (config.getInitialize()) { // 初始化数据库
                initialize(dataSource);
            }
            return dataSource;
        }
        return null;
    }

    private void initialize(DataSource dataSource) {
        ClassPathResource schemaResource = new ClassPathResource("schema.sql");
        ClassPathResource dataResource = new ClassPathResource("data.sql");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(schemaResource, dataResource);
        populator.execute(dataSource);
    }

}
