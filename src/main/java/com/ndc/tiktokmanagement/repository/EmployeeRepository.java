package com.ndc.tiktokmanagement.repository;

import com.ndc.tiktokmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);

}
