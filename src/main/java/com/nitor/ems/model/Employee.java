package com.nitor.ems.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Person {
    @Id
    private Long employeeId;
    @NotBlank(message = "Department cannot be empty")
    private String department;
    @NotBlank(message = "Designation cannot be empty")
    private String designation;
    @NotNull(message = "Salary cannot be null or empty")
    private Double salary;
    @NotNull(message = "Joining Date cannot be null or empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joiningDate;

}

