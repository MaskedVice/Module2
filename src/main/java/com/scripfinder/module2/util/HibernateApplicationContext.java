package com.scripfinder.module2.util;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableTransactionManagement
@PropertySource({
    "classpath:hibernate.properties",
    "classpath:mainDataBase.properties"
})
@ComponentScan({
    "com.upstoxup.scripfinder.Module2.*",
})

public class HibernateApplicationContext {
    
    private static final String AUTOCOMMIT = "hibernate.connection.autocommit";

    @Value("${mainDataBase.user}")
    private String userName;

    @Value("${mainDataBase.password}")
    private String password;

    @Value("${mainDataBase.url}")
    private String url;

    @Value("${mainDataBase.driver}")
    private String driver;

    @Value("${mainDataBase.maximumPoolSize}")
    private String maximumPoolSize;

    @Value("${mainDataBase.validationTimeout}")
    private String validationTimeout;

    @Value("${mainDataBase.connectionTimeout}")
    private String connectionTimeout;

    @Value("${mainDataBase.maxLifeTime}")
    private String maxLifeTime;

    @Value("${mainDataBase.minLifeTime}")
    private String minLifeTime;


    @Bean(name = "mainDataSource",destroyMethod = "close")
    @Primary
    public DataSource mainDataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setMaximumPoolSize(Integer.parseInt(maximumPoolSize));
        dataSource.setValidationTimeout(Integer.parseInt(validationTimeout));
        dataSource.setConnectionTimeout(Integer.parseInt(connectionTimeout));
        dataSource.setMaxLifetime(Integer.parseInt(maxLifeTime));
        dataSource.setMinimumIdle(Integer.parseInt(minLifeTime));
        dataSource.setAutoCommit(Boolean.parseBoolean(AUTOCOMMIT));
        return dataSource;
    }


    @Bean(name = "mainSessionFactory")
    @Autowired
    @Primary
    public LocalSessionFactoryBean getMainSessionFactory(@Qualifier("mainDataSource") DataSource mainDataSource,Properties hibernatProperties){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(mainDataSource);
        sessionFactory.setHibernateProperties(hibernatProperties);
        return sessionFactory;
    }


    @Bean(name = "mainTransactionManager")
    @Autowired
    @Primary
    public HibernateTransactionManager getTransactionManager(@Qualifier("mainSessionFactory") SessionFactory mainSessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(mainSessionFactory);
        return transactionManager;
    }


}
