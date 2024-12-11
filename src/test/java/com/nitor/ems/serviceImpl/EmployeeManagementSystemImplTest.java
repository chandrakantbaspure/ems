package com.nitor.ems.serviceImpl;

import com.nitor.ems.model.Employee;
import com.nitor.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeManagementSystemImplTest {
    @InjectMocks
    private EmployeeManagementSystemImpl employeeManagementSystem;

    private static EmployeeRepository employeeRepository;

    @Mock
    private Employee employee;

    @BeforeAll
    public static void setUpClass() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setName("John Doe");
        employee.setAge(21);
        employee.setDepartment("IT");
        employee.setDesignation("Software Engineer");
        employee.setSalary(50000.0);
        employee.setJoiningDate(LocalDate.of(2022, 1, 1));
        Employee employee1 = new Employee();
        employee1.setEmployeeId(2L);
        employee1.setName("Jane Smith");
        employee1.setAge(25);
        employee1.setDepartment("HR");
        employee1.setDesignation("HR Manager");
        employee1.setSalary(60000.0);
        employee1.setJoiningDate(LocalDate.of(2022, 2, 1));
        Employee employee2 = new Employee();
        employee2.setEmployeeId(3L);
        employee2.setName("Bob Johnson");
        employee2.setAge(29);
        employee2.setDepartment("Finance");
        employee2.setDesignation("Accountant");
        employee2.setSalary(55000.0);
        employee2.setJoiningDate(LocalDate.of(2022, 3, 1));
        employeeRepository.save(employee2);
        employeeRepository.save(employee);
        employeeRepository.save(employee1);
        Mockito.when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));
        Mockito.when(employeeRepository.findAll()).thenReturn(List.of(employee));
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
    }



    @Test
    void updateEmployee() {
        Employee employee1 = employeeManagementSystem.updateEmployee(employee,1L);
        Assertions.assertNotNull(employee1);
        Assertions.assertEquals(1L, employee1.getEmployeeId());
    }

    @Test
    void findEmployeeById() {
        Employee employee1 = employeeManagementSystem.findEmployeeById(1L);
        verify(employeeRepository, Mockito.times(1)).findById(1L);
        Assertions.assertNotNull(employee1);
        Mockito.verifyNoMoreInteractions(employee);
        Assertions.assertEquals(1L, employee1.getEmployeeId());

    }

    @Test
    void deleteEmployeeById() {
        employeeManagementSystem.deleteEmployeeById(1L);
        verify(employeeRepository, Mockito.times(1)).deleteById(1L);
        Mockito.verifyNoMoreInteractions(employee);
    }

    @Test
    void getAllEmployees() {
        List<Employee> allEmployees = employeeManagementSystem.getAllEmployees();
        System.out.println(allEmployees);
        Mockito.verifyNoMoreInteractions(employee);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(1, allEmployees.size());

    }

    @Test
    void findEmployeesAfterJoiningDate() {
    }

    @Test
    void findEmployeesByDepartment() {
        Mockito.when(employeeRepository.findByDepartment("IT")).thenReturn(List.of(employee));
        List<Employee> employeesByDepartment = employeeManagementSystem.findEmployeesByDepartment("IT");
        Assertions.assertNotNull(employeesByDepartment);
        Assertions.assertEquals(1, employeesByDepartment.size());
        Mockito.verifyNoMoreInteractions(employee);
    }

    @Test
    void sortEmployeesByParameters() {
    }

    @Test
    void findByAgeBetween() {
        Mockito.when(employeeRepository.findByAgeBetween(20, 30)).thenReturn(List.of(employee));
        List<Employee> employeesByAge = employeeManagementSystem.findByAgeBetween(20, 30);
        Assertions.assertNotNull(employeesByAge);
        Assertions.assertEquals(1, employeesByAge.size());
        Mockito.verifyNoMoreInteractions(employee);
    }
}