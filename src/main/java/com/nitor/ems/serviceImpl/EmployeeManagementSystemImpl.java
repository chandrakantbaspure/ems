package com.nitor.ems.serviceImpl;

import com.nitor.ems.exception.EmployeeNotFoundException;
import com.nitor.ems.model.Employee;
import com.nitor.ems.repository.EmployeeRepository;
import com.nitor.ems.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.*;

@Transactional
@Service
public class EmployeeManagementSystemImpl implements EmployeeService {
    private final Logger log = LoggerFactory.getLogger(EmployeeManagementSystemImpl.class);
    private static final String EMPLOYEE_NOT_FOUND = "Employee not found: {}";
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

        if (employee.getEmployeeId() != null && employeeRepository.existsById(employee.getEmployeeId())) {
            throw new IllegalStateException("Employee with ID " + employee.getEmployeeId() + " already exists");
        }
        Random random = new Random();
        employee.setEmployeeId(random.nextLong(10000));
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
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employee.get();
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
        log.info("Searching employees by department: {}", department);
        return employeeRepository.findByDepartment(department);
    }

    @Override
    public List<Employee> sortEmployeeByJoiningDate() {
        return employeeRepository.sortEmployeeByJoiningDate();
    }

    @Override
    public List<Employee> sortEmployeesByParameters(String field, String order) {
        Sort.Direction direction = Sort.Direction.fromString(order);
        return employeeRepository.findAll(Sort.by(direction, field));
    }

    @Override
    public List<Employee> findByAgeBetween(Integer startingAge, Integer endingAge) {
        log.info("Finding employees with age between {} and {}", startingAge, endingAge);
        return employeeRepository.findByAgeBetween(startingAge, endingAge);
    }
}
