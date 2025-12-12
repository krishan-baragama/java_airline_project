package com.timeoutairline.booking_system.repository;

import com.timeoutairline.booking_system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByNumEmp(String numEmp);
} 
