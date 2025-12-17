package com.example.employeeapi.repo;

import com.example.employeeapi.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  @Query("""
    select e from Employee e
    where (:kw is null or :kw = ''
      or lower(e.employeeNo) like lower(concat('%', :kw, '%'))
      or lower(e.name) like lower(concat('%', :kw, '%'))
      or lower(e.email) like lower(concat('%', :kw, '%'))
    )
    order by e.id desc
  """)
  List<Employee> search(@Param("kw") String keyword);
}
