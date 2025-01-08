package com.ndc.tiktokmanagement.service;

import com.ndc.tiktokmanagement.model.Employee;
import com.ndc.tiktokmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean checkLogin(String phoneNumber, String password) {
        Employee employee = findByPhoneNumber(phoneNumber);
        return employee != null && employee.getPassword().equals(password);
    }

    public Employee findByPhoneNumber(String phoneNumber) {
        return employeeRepository.findByPhoneNumber(phoneNumber);
    }
    // Get all shippers
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }
    // Create a new employee
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update shipper details
    public Employee updateEmployee(Long id, Employee employeeDetails) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception("Employee not found"));
        employee.setFullName(employeeDetails.getFullName());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        return employeeRepository.save(employee);
    }

    // Delete a shipper
    public void deleteEmployee(Long id) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception("Employee not found"));
        employeeRepository.delete(employee);
    }
    public void handleOrders() {}
    public void handleComplaints() {}
    public void assginTrack() {}
    public void trackPerformance() {}

}
