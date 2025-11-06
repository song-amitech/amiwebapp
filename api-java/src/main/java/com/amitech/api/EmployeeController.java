package com.amitech.api;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {
  private final EmployeeRepository repo;
  public EmployeeController(EmployeeRepository repo) { this.repo = repo; }

  @GetMapping("/health")
  public Map<String, Object> health() {
    return Map.of("ok", true);
  }

  @GetMapping("/employees")
  public List<Employee> employees(@RequestParam(value="query", required=false) String q) {
    return repo.search(q);
  }
}
