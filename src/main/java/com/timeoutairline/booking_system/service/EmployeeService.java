package com.timeoutairline.booking_system.service;

import com.timeoutairline.booking_system.model.Employee;
import com.timeoutairline.booking_system.model.User;
import com.timeoutairline.booking_system.repository.EmployeeRepository;
import com.timeoutairline.booking_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    
    public Employee createEmployee(Employee employee, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        employee.setUser(user);
        return employeeRepository.save(employee);
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employee.setNumEmp(employeeDetails.getNumEmp());
        employee.setProfession(employeeDetails.getProfession());
        employee.setTitle(employeeDetails.getTitle());
        if (employeeDetails.getUser() != null) {
            employee.setUser(employeeDetails.getUser());
        }
        return employeeRepository.save(employee);
    }
    
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}