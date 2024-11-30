package com.oven.multitenant;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.oven.multitenant.MultiTenantConstants.DEFAULT_TENANT_ID;

/**
 * 提供动态数据源
 */
@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    @Resource
    private DataSource defaultDataSource;

    @Resource
    private ApplicationContext context;

    static Map<String, DataSource> map = new HashMap<>();

    boolean init = false;

    @PostConstruct
    public void load() {
        map.put(DEFAULT_TENANT_ID, defaultDataSource);
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return map.get(DEFAULT_TENANT_ID);
    }

    @Override
    public DataSource selectDataSource(String tenantIdentifier) {
        if (!init) { // 初始化所有数据源
            init = true;
            TenantDataSource tenantDataSource = context.getBean(TenantDataSource.class);
            map.putAll(tenantDataSource.getAll());
        }
        return map.get(tenantIdentifier);

        // // 懒加载模式
        // DataSource dataSource = map.get(tenantIdentifier);
        // if (dataSource == null) {
        //     TenantDataSource tenantDataSource = context.getBean(TenantDataSource.class);
        //     dataSource = tenantDataSource.getDataSource(tenantIdentifier);
        //     map.put(tenantIdentifier, dataSource);
        // }
        // return dataSource;
    }

}