package com.darts.orcl;

import org.jboss.resteasy.plugins.stats.RegistryStatsResource;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jpc on 12/29/16.
 */

@ApplicationPath("/")
public class OrclApplication extends Application {
    private EntityManagerFactory factory;

    @Override
    public Set<Class<?>> getClasses(){
        return new HashSet<Class<?>>(Arrays.asList(OrclResource.class, RegistryStatsResource.class));
    }

    @Produces
    @ApplicationScoped
    public EntityManagerFactory produceEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("orcl");
    }
}
