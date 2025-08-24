package io.github.kvr_10.ems.repository;

import io.github.kvr_10.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
