package com.example.service;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmps(){
        return employeeRepo.findAll();
    }

    public Employee addEmployee(Employee employee) throws Exception {
       if(employee.getName().length()>5)
           throw new Exception("Sorry please reduce size of your name");

        return employeeRepo.save(employee);
    }

}
