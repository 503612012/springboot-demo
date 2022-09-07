package com.oven.dao;

import com.oven.multitenant.DataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.oven.multitenant.MultiTenantConstants.TENANT_KEY;

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
     *
     * @author Oven
     */
    protected JdbcTemplate getJdbcTemplate() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        HttpServletRequest req = attributes.getRequest();
        String tenant = req.getHeader(TENANT_KEY);
        if (StringUtils.isEmpty(tenant)) {
            return null;
        }
        return new JdbcTemplate(datasourceProvider.selectDataSource(tenant));
    }

}
