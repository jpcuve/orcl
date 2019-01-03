package com.messio.orcl;

import com.messio.orcl.domain.Department;
import com.messio.orcl.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jpc on 1/20/17.
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger LOGGER = Logger.getLogger(ApiController.class.getCanonicalName());
    @Autowired
    private PersistenceFacade facade;

    @GetMapping(value = "/departments")
    public List<Department> findDepartments(){
        return facade.findDeparments();
    }

    @GetMapping(value = "/employees")
    public List<Employee> findEmployees(){
        return facade.findEmployees();
    }
}
