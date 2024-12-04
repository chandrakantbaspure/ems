package com.nitor.ems.serviceImpl;

import com.nitor.ems.exception.EmployeeNotFoundException;
import com.nitor.ems.model.Employee;
import com.nitor.ems.repository.EmployeeRepository;
import com.nitor.ems.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeManagementSystemImpl implements EmployeeService {
    private static final String EMPLOYEE_FILTER_LOG = "Searching employee by joining date: {}";
    private static final String EMPLOYEE_TENURE_LOG = "Calculating employee tenure: {}";
    private final Logger log = LoggerFactory.getLogger(EmployeeManagementSystemImpl.class);
    private static final String EMPLOYEE_NOT_FOUND = "Employee not found: {}";
    private static final String EMPLOYEE_FOUND = "Employee found: {}";
    private static final String EMPLOYEE_UPDATED = "Employee updated: {}";
    private static final String EMPLOYEE_ADD_INFO = "Adding new employee";
    private static final String NEW_EMPLOYEE_LOG = "New employee added: {}";
    private static final String EMPLOYEE_UPDATE_LOG = "Updating employee: {}";
    private static final String ALL_EMPLOYEES_LOG = "Getting all employees";
    private static final String EMPLOYEE_DELETE_LOG = "Deleting employee: {}";
    private static final String EMPLOYEE_DELETED_SUCCESS_LOG_MESSAGE = "Successfully deleted employee with ID: {}";
    private static final String EMPLOYEE_SEARCH_LOG = "Searching employee by ID: {}";


    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeManagementSystemImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addNewEmployee(Employee employee) {
        log.info(EMPLOYEE_ADD_INFO);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee, Long empId) {
        log.info(EMPLOYEE_UPDATE_LOG, employee);
        Employee existingEmployee = employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        existingEmployee.setName(employee.getName());
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setJoiningDate(employee.getJoiningDate());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        log.info(EMPLOYEE_SEARCH_LOG, id);
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        log.info(EMPLOYEE_DELETE_LOG, employeeId);

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            employeeRepository.deleteById(employeeId);
            log.info(EMPLOYEE_DELETED_SUCCESS_LOG_MESSAGE, employeeId);
        } else {
            log.warn(EMPLOYEE_NOT_FOUND, employeeId);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        log.info(ALL_EMPLOYEES_LOG);
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(e -> {
            e.setName(e.getName());
            e.setAge(e.getAge());
            e.setAddress(e.getAddress());
        });
        return employees;
    }

    @Override
    public List<Employee> findEmployeesBeforeJoiningDate(LocalDate joiningDate) {
        return employeeRepository.findByJoiningDateBefore(joiningDate);
    }

    @Override
    public List<Employee> findEmployeesAfterJoiningDate(LocalDate joiningDate) {
        return employeeRepository.findByJoiningDateAfter(joiningDate);
    }

    @Override
    public List<Employee> findEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    public List<Employee> sortEmployeeByJoiningDate() {
        return employeeRepository.sortEmployeeByJoiningDate();
    }


    @Override
    public List<Employee> findByAgeBetween(Integer startingAge, Integer endingAge) {
        return employeeRepository.findByAgeBetween(startingAge, endingAge);
    }
}
