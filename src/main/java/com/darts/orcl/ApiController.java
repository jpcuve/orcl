package com.darts.orcl;

import com.darts.orcl.entity.Department;
import com.darts.orcl.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jpc on 1/20/17.
 */
@RestController
@RequestMapping("/")
public class ApiController {
    private static final Logger LOGGER = Logger.getLogger(ApiController.class.getCanonicalName());
    @Autowired
    private PersistenceFacade facade;

    @RequestMapping(value = "/departments", method = RequestMethod.GET, produces = "application/json")
    public List<Department> findDepartments(){
        return facade.findDeparments();
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
    public List<Employee> findEmployees(){
        return facade.findEmployees();
    }
}
