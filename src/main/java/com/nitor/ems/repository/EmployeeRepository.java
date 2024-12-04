package com.nitor.ems.repository;

import com.nitor.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(String department);

    @Query("SELECT e FROM Employee e ORDER BY e.joiningDate")
    List<Employee> sortEmployeeByJoiningDate();

    List<Employee> findByAgeBetween(Integer ageAfter, Integer ageBefore);

    List<Employee> findByJoiningDateAfter(LocalDate date);

    List<Employee> findByJoiningDateBefore(LocalDate date);
}
