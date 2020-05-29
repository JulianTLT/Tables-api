package com.microservices.tablesservice.tablesservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@PropertySource("classpath:application.properties")
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.microservices.tablesservice")
@EnableJpaRepositories("com.microservices.tablesservice")
public class DatabaseConfig {
    @Value("${hibernate.driver}")
    String driverClassName;
    @Value("${hibernate.url}")
    String url;
    @Value("${hibernate.username}")
    String username;
    @Value("${hibernate.password}")
    String password;
    @Value("${hibernate.hbm2ddl}")
    String update;
    @Value("${hibernate.dialect}")
    String dialect;

    private static final String HBM2DDL = "hibernate.hbm2ddl.auto";
    private static final String DIALECT = "hibernate.dialect";


    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);

        return dataSourceBuilder.build();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("com.microservices.tablesservice.entity");
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        Map<String, String> map = new HashMap<>();
        map.put(HBM2DDL, update);
        map.put(DIALECT, dialect);
        hibernateProperties.putAll(map);
        return hibernateProperties;
    }
}
