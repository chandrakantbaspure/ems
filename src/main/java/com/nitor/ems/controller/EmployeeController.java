package com.nitor.ems.controller;

import com.nitor.ems.exception.EmployeeMismatchException;
import com.nitor.ems.exception.EmployeeNotFoundException;
import com.nitor.ems.model.Employee;
import com.nitor.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {
    private static final Log log = LogFactory.getLog(EmployeeController.class);

    private final EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("Request to get all employees");
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/fetchEmployee/{empId}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long empId) {
        log.info("Request to find employee by id: " + empId);
        Employee employee = employeeService.findEmployeeById(empId);
        if (employee == null) {
            log.info("Employee not found: " + empId);
            throw new EmployeeNotFoundException("Employee not found with id: " + empId);
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addNewEmployee(@Valid @RequestBody Employee employee) {
        log.info("Request to add new employee: " + employee);
        return new ResponseEntity<>(employeeService.addNewEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/updateEmployee/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long empId, @Valid @RequestBody Employee updatedEmployee) {
        log.info("Request to update employee with id: " + empId);
        Employee employee = employeeService.findEmployeeById(empId);
        if (!updatedEmployee.getEmployeeId().equals(empId)) {
            throw new EmployeeMismatchException("Employee with id " + employee.getEmployeeId() + " not matching id " + empId);
        } else if (employeeService.findEmployeeById(empId) == null || !employeeService.findEmployeeById(empId).equals(empId)) {
            throw new EmployeeNotFoundException("Employee is not found: " + empId);
        }
        return ResponseEntity.ok(employeeService.updateEmployee(updatedEmployee, empId));
    }

    @DeleteMapping("/deleteEmployee/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long empId) {
        log.info("Request to delete employee with id: " + empId);
        Employee employee = employeeService.findEmployeeById(empId);
        if (employee == null) {
            log.info("Employee not found: " + empId);
            throw new EmployeeNotFoundException("Employee not found with id: " + empId);
        }
        employeeService.deleteEmployeeById(empId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/beforejoiningDate/{joinDate}")
    public ResponseEntity<List<Employee>> findEmployeesBeforeJoiningDate(@PathVariable LocalDate joinDate) {
        log.info("Request to find employees before joining date: " + joinDate);
        List<Employee> employees = employeeService.findEmployeesBeforeJoiningDate(joinDate);
        if (employees.isEmpty()) {
            log.info("No employees found with joining date: " + joinDate);
            throw new EmployeeNotFoundException("No employees found with joining date: " + joinDate);
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/afterjoiningdate/{joinDate}")
    public ResponseEntity<List<Employee>> findEmployeesAfterJoiningDate(@PathVariable LocalDate joinDate) throws EmployeeNotFoundException {
        log.info("Request to find employees after joining date: " + joinDate);
        List<Employee> employees = employeeService.findEmployeesAfterJoiningDate(joinDate);
        if (employees.isEmpty()) {
            log.info("No employees found with joining date: " + joinDate);
            throw new EmployeeNotFoundException("No employees found with joining date: " + joinDate);
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/department/{deptName}")
    public ResponseEntity<List<Employee>> findEmployeesByDepartment(@PathVariable String deptName) {
        log.info("Request to find employees by department: " + deptName);
        List<Employee> employees = employeeService.findEmployeesByDepartment(deptName);
        if (employees.isEmpty()) {
            log.info("No employees found with department: " + deptName);
            throw new EmployeeNotFoundException("No employees found with department: " + deptName);
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/sortemployees")
    public ResponseEntity<List<Employee>> findEmployeesSortedByJoiningDate() {
        log.info("Request to find employees sorted by joining date");
        List<Employee> employees = employeeService.sortEmployeeByJoiningDate();
        if (employees.isEmpty()) {
            log.info("No employees found");
            throw new EmployeeNotFoundException("No employees found");
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("findbetweenage/{startingAge}/{endingAge}")
    public ResponseEntity<List<Employee>> findEmployeesBetweenAge(@PathVariable Integer startingAge, @PathVariable Integer endingAge) {
        log.info("Request to find employees between age: " + startingAge + " and " + endingAge);
        List<Employee> employees = employeeService.findByAgeBetween(startingAge, endingAge);
        if (employees.isEmpty()) {
            log.info("No employees found between age: " + startingAge + " and " + endingAge);
            throw new EmployeeNotFoundException("No employees found between age: " + startingAge + " and " + endingAge);
        }
        return ResponseEntity.ok(employees);
    }
}
