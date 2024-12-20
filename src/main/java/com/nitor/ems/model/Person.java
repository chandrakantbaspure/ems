package com.nitor.ems.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass
public class Person {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Age cannot be null or empty")
    private Integer age;
    @NotBlank(message = "Address cannot be empty")
    private String address;
}