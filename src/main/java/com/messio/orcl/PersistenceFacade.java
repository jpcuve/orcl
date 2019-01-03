package com.messio.orcl;

import com.messio.orcl.domain.Department;
import com.messio.orcl.domain.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by jpc on 1/19/17.
 */
@Repository
public class PersistenceFacade {
    @PersistenceContext
    private EntityManager em;

    public <T> T create(T t){
        Objects.requireNonNull(t);
        em.persist(t);
        em.flush();
        em.refresh(t);
        return t;
    }

    public <T, ID extends Serializable> Optional<T> findOne(Class<T> clazz, ID pk){
        Objects.requireNonNull(pk);
        return Optional.ofNullable(em.find(clazz, pk));
    }

    public <T> T update(T t){
        Objects.requireNonNull(t);
        return em.merge(t);
    }

    public <T, ID extends Serializable> void delete(Class<T> clazz, ID pk){
        Objects.requireNonNull(pk != null);
        em.remove(em.getReference(clazz, pk));
    }

    public <T, ID extends Serializable> List<T> reorder(List<ID> pks, List<T> ts, Function<T, ID> keyMapper){
        final List<T> list = new ArrayList<>(ts);
        ts.forEach(t -> list.set(pks.indexOf(keyMapper.apply(t)), t));
        return list;
    }

    public List<Department> findDeparments(){
        return em.createNamedQuery(Department.DEPARTMENT_ALL, Department.class).getResultList();
    }

    public List<Employee> findEmployees(){
        return em.createNamedQuery(Employee.EMPLOYEE_ALL, Employee.class).getResultList();
    }
}
