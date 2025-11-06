package com.amitech.api;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
  private final JdbcTemplate jdbc;

  public EmployeeRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  public List<Employee> search(String q) {
    String base = """
      SELECT employee_no, full_name, department, email
      FROM employees
      """;
    if (q == null || q.isBlank()) {
      return jdbc.query(base + " ORDER BY employee_no ASC LIMIT 50",
        (rs, i) -> map(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
    }
    String like = "%" + q.trim() + "%";
    return jdbc.query(base + " WHERE employee_no ILIKE ? OR full_name ILIKE ? OR department ILIKE ? ORDER BY employee_no ASC LIMIT 50",
      ps -> { ps.setString(1, like); ps.setString(2, like); ps.setString(3, like); },
      (rs, i) -> map(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
  }

  private Employee map(String no, String name, String dept, String email) {
    Employee e = new Employee();
    e.employee_no = no; e.full_name = name; e.department = dept; e.email = email;
    return e;
  }
}
