package com.darts.orcl;

import com.darts.orcl.entity.Department;
import com.darts.orcl.entity.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpc on 12/30/16.
 */
@Path("/orcl")
@Produces({"application/xml"})
public class OrclResource {
    @Inject
    private EntityManagerFactory emf;
    @GET
    @Path("/test")
    public String test() {
        return "test";
    }

    @GET
    @Path("/departments")
    public ArrayList<Department> findDepartments(){
        final EntityManager em = emf.createEntityManager();
        final List<Department> departments = em.createQuery("select d from Department d", Department.class).setMaxResults(100).getResultList();
        em.close();
        return new ArrayList<>(departments);
    }

    @GET
    @Path("/employees")
    public ArrayList<Employee> findEmployees(){
        final EntityManager em = emf.createEntityManager();
        final List<Employee> employees = em.createQuery("select e from Employee e", Employee.class).setMaxResults(100).getResultList();
        em.close();
        return new ArrayList<>(employees);
    }
}
