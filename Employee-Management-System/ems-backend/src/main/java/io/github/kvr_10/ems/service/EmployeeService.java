package io.github.kvr_10.ems.service;

import io.github.kvr_10.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto updateEmployee(Long employeeID, EmployeeDto updatedEmployee);

    void deleteEmployee(Long employeeID);
}
