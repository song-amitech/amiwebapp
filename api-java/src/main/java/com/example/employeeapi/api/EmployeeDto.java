package com.example.employeeapi.api;

import com.example.employeeapi.domain.Employee;

public record EmployeeDto(
    Long id,
    String employeeNo,
    String name,
    String department,
    String email
) {
  public static EmployeeDto from(Employee e) {
    return new EmployeeDto(
        e.getId(),
        e.getEmployeeNo(),
        e.getName(),
        e.getDepartment(),
        e.getEmail()
    );
  }
}
