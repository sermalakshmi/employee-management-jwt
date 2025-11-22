
package com.serma.employee.service;

import com.serma.employee.entity.Employee;
import com.serma.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee create(Employee emp) { return repo.save(emp); }

    public Employee update(Long id, Employee emp) {
        Employee existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setFirstName(emp.getFirstName());
        existing.setLastName(emp.getLastName());
        existing.setEmail(emp.getEmail());
        existing.setDepartment(emp.getDepartment());
        existing.setRole(emp.getRole());
        existing.setPhone(emp.getPhone());
        return repo.save(existing);
    }

    public List<Employee> getAll() { return repo.findAll(); }

    public Employee getById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void delete(Long id) { repo.deleteById(id); }
}
