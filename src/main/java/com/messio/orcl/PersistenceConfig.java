package com.messio.orcl;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource){
        final SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:changelog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
