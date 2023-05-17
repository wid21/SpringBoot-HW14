package com.example.springboothw14.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Year;

import java.util.Date;

@AllArgsConstructor
@Data
public class Employee {
    @NotEmpty(message = "id should not be empty")
    @Size(min=3,message = "the id should be more 2")
    private String id;

    @NotEmpty(message = "name should not be empty")
    @Size(min=5,message = "name  should be more 4")
    private String name;

    @NotNull(message = "age should not be empty")
    @Positive(message = "must enter number")
    @Min(value = 26,message = "the age should above 25")
    private int age;

    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp ="^(supervisor|coordinator)$",message = "role should be supervisor or coordinator ")
    private String role;

    @AssertFalse(message = "on leave must be false")
    @NotNull(message = "on leave should not be empty")
    private boolean onLeave ;

    @NotNull(message = "year can not be null")
    @Positive(message = "employment year must be a positive value")
    @Min(value = 1800, message = "employment year must be start from 1800")
    @Max(value = 2023, message = "employment year must be less than or equal to 2023")
    private int employmentYear;

    @NotNull(message = "annual Leave can not be null")
    @PositiveOrZero(message = "annual Leave must enter number")
    private int annualLeave;
}
