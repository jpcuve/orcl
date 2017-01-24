package com.darts.orcl;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * Created by jpc on 1/19/17.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.darts.orcl")
public class ApplicationConfiguration {
    private Properties properties = new Properties();

    public ApplicationConfiguration() {
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")){
            properties.load(is);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.darts.orcl.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        final Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        emf.setJpaProperties(props);
        return emf;
    }

/*
    @Bean
    public DataSource dataSource(){
        final DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(properties.getProperty("spring.datasource.driver-class-name"));
        ds.setUrl(properties.getProperty("spring.datasource.url"));
        ds.setUsername(properties.getProperty("spring.datasource.username"));
        ds.setPassword(properties.getProperty("spring.datasource.password"));
        return ds;
    }
*/

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .build();
    }

    @Bean
    public SpringLiquibase liquibase(){
        final SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:changelog.xml");
        return liquibase;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        return new JpaTransactionManager(emf);
    }
}

