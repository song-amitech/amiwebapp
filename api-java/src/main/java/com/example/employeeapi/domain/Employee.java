package com.example.employeeapi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "employee_no", nullable = false, unique = true, length = 20)
  private String employeeNo;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(length = 100)
  private String department;

  @Column(length = 200)
  private String email;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getEmployeeNo() { return employeeNo; }
  public void setEmployeeNo(String employeeNo) { this.employeeNo = employeeNo; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getDepartment() { return department; }
  public void setDepartment(String department) { this.department = department; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
}
