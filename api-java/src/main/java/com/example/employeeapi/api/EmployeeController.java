package com.example.employeeapi.api;

import com.example.employeeapi.repo.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/employees")
@RequestMapping("/employees")
public class EmployeeController {

  private final EmployeeRepository repository;

  public EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<EmployeeDto> search(
      @RequestParam(name = "keyword", required = false) String keyword) {

    return repository.search(keyword)
        .stream()
        .map(EmployeeDto::from)
        .toList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
    return repository.findById(id)
        .map(EmployeeDto::from)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
