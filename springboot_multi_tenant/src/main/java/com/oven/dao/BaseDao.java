package com.oven.dao;

import com.oven.multitenant.DataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.oven.multitenant.MultiTenantConstants.CURRENT_TENANT_IDENTIFIER;

/**
 * baseDao
 *
 * @author Oven
 */
public class BaseDao {

    @Autowired
    private DataSourceBasedMultiTenantConnectionProviderImpl datasourceProvider;

    /**
     * 动态获取JdbcTemplate，根据租户信息
     */
    protected JdbcTemplate getJdbcTemplate() {
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req =  attributes.getRequest();
        String tanant = (String) req.getAttribute(CURRENT_TENANT_IDENTIFIER);
        return new JdbcTemplate(datasourceProvider.selectDataSource(tanant));
    }

}
