package com.messio.orcl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigInteger;

/**
 * Created by jpc on 12/30/16.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQueries({
        @NamedQuery(name = Employee.EMPLOYEE_ALL, query = "select e from Employee e order by e.name")
})
@Table(name = "emp")
public class Employee {
    public static final String EMPLOYEE_ALL = "employee.all";
    @Id
    @Column(name = "empno")
    private Integer id;
    @Basic
    @Column(name = "ename")
    private String name;
    @Basic
    @Column(name = "job")
    private String job;
    @JsonIgnore
    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mgr")
    private Employee manager;
    @Basic
    @Column(name = "sal")
    private BigInteger salary;
    @JsonIgnore
    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depno")
    private Department department;
    @Basic
    @Column(name = "mgr", insertable = false, updatable = false)
    private Integer mgr;
    @Basic
    @Column(name = "depno", insertable = false, updatable = false)
    private Integer depno;

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public BigInteger getSalary() {
        return salary;
    }

    public void setSalary(BigInteger salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Integer getDepno() {
        return depno;
    }

    public void setDepno(Integer depno) {
        this.depno = depno;
    }
}
