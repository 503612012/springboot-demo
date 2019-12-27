package com.oven.multitenant;

import org.hibernate.MultiTenancyStrategy;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.annotation.Resource;
import javax.persistence.PersistenceContext;
import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多租户配置
 *
 * @author Oven
 */
@Configuration
public class MultiTenantConfig {

    @Resource
    private DataSourceBasedMultiTenantConnectionProviderImpl datasourceProvider;

    @Resource
    private TenantIdentifierResolver tenantResolver;

    @Resource
    private AutowireCapableBeanFactory beanFactory;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
        return new DriverManagerDataSource();
    }

    @Bean
    @Primary
    @PersistenceContext
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.multiTenancy", MultiTenancyStrategy.DATABASE.name());
        props.put("hibernate.multi_tenant_connection_provider", datasourceProvider);
        props.put("hibernate.tenant_identifier_resolver", tenantResolver);

        LocalContainerEntityManagerFactoryBean result = builder.dataSource(dataSource())
                .persistenceUnit(MultiTenantConstants.TENANT_KEY)
                .properties(props)
                .packages("com.oven")
                .build();
        result.setJpaVendorAdapter(jpaVendorAdapter());
        return result;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public FilterRegistrationBean<Filter> myFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        Filter tenantFilter = new MultiTenantFilter();
        beanFactory.autowireBean(tenantFilter);
        registration.setFilter(tenantFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

}
