
package com.serma.employee.controller;

import com.serma.employee.entity.Employee;
import com.serma.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService svc;

    public EmployeeController(EmployeeService svc) { this.svc = svc; }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee emp) {
        return ResponseEntity.ok(svc.create(emp));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> all() {
        return ResponseEntity.ok(svc.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee emp) {
        return ResponseEntity.ok(svc.update(id, emp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        svc.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
