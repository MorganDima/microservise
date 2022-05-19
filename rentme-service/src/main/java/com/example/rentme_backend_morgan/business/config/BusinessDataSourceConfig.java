package com.example.rentme_backend_morgan.business.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.rentme_backend_morgan.business",
        entityManagerFactoryRef = "businessEntityManagerFactory",
        transactionManagerRef= "businessTransactionManager")
public class BusinessDataSourceConfig {


    @Bean(name = "businessDataSourceProperties")
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "businessDataSource")
    public DataSource dataSource(@Qualifier("businessDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "businessEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("businessDataSource") DataSource businessDataSource) {
        return builder
                .dataSource(businessDataSource)
                .packages("com.example.rentme_backend_morgan.business")
                .build();
    }

    @Bean(name = "businessTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("businessEntityManagerFactory") EntityManagerFactory businessEntityManagerFactory) {
        return new JpaTransactionManager(businessEntityManagerFactory);
    }
}