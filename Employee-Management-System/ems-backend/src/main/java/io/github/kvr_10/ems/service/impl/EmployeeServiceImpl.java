package io.github.kvr_10.ems.service.impl;

import io.github.kvr_10.ems.dto.EmployeeDto;
import io.github.kvr_10.ems.entity.Employee;
import io.github.kvr_10.ems.exception.ResourceNotFoundException;
import io.github.kvr_10.ems.mapper.EmployeeMapper;
import io.github.kvr_10.ems.repository.EmployeeRepository;
import io.github.kvr_10.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

   private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee does not exist with given id: "+employeeId ));

       return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeID, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeID).orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exist with given id: "+employeeID)
        );

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeID) {

        Employee employee = employeeRepository.findById(employeeID).orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exist with given id: "+employeeID)
        );

        employeeRepository.deleteById(employeeID);
    }
}
