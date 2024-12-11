package com.nitor.ems.service;

import com.nitor.ems.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    Employee addNewEmployee(Employee employee);

    Employee updateEmployee(Employee employee, Long empId);

    Employee findEmployeeById(Long id);

    void deleteEmployeeById(Long id);

    List<Employee> getAllEmployees();

    List<Employee> findEmployeesBeforeJoiningDate(LocalDate joiningDate);

    List<Employee> findEmployeesAfterJoiningDate(LocalDate joiningDate);

    List<Employee> findEmployeesByDepartment(String department);

    List<Employee> sortEmployeeByJoiningDate();

    public List<Employee> sortEmployeesByParameters(String sortBy, String order);
    List<Employee> findByAgeBetween(Integer startingAge, Integer endingAge);
}
