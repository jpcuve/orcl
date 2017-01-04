package com.darts.orcl;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by jpc on 12/30/16.
 */
public class OrclServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/orcl", "scott", "tiger");
            final Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            final ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();
            final Liquibase liquibase = new Liquibase("changelog.xml", resourceAccessor, database);
//            liquibase.dropAll();
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
