package com.bracu.rsmr.Employee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/{id}/approveTransaction")
    public void approveTransaction(@PathVariable Long id, @RequestParam Long transactionId) {
        Employee employee = employeeService.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeService.approveTransaction(transactionId, employee);
    }
}
