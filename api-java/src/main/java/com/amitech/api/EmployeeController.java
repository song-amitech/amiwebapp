package com.amitech.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("ok", true);
    }

    @GetMapping("/employees")
    public List<Map<String, Object>> employees() {
        return List.of(
                Map.of("employee_no", "1001", "full_name", "山田 太郎", "department", "営業", "email", "taro@example.com"),
                Map.of("employee_no", "1002", "full_name", "佐藤 花子", "department", "総務", "email", "hanako@example.com")
        );
    }
}
