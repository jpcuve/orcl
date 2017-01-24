package com.darts.orcl.entity;

import javax.persistence.*;

/**
 * Created by jpc on 12/30/16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Department.DEPARTMENT_ALL, query = "select d from Department d order by d.name")
})
@Table(name = "dept")
public class Department {
    public static final String DEPARTMENT_ALL = "department.all";
    @Id
    @Column(name = "depno")
    private Integer id;
    @Basic
    @Column(name = "dname")
    private String name;
    @Basic
    @Column(name = "loc")
    private String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
