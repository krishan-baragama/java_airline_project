package com.timeoutairline.booking_system.controller;

import com.timeoutairline.booking_system.model.Employee;
import com.timeoutairline.booking_system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    // Create a new Employee linked to a User
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> request) {
        Employee employee = new Employee();
        employee.setNumEmp((String) request.get("numEmp"));
        employee.setProfession((String) request.get("profession"));
        employee.setTitle((String) request.get("title"));
        Long userId = Long.valueOf(request.get("userId").toString());
        
        Employee createdEmployee = employeeService.createEmployee(employee, userId);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
    
    // Read/Display all Employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    // Read/Display a specific Employee
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Update an Employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employee);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Delete an Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}