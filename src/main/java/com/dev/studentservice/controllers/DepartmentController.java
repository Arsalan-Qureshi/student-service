package com.dev.studentservice.controllers;

import com.dev.studentservice.models.Department;
import com.dev.studentservice.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public Iterable<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public Optional<Department> getDepartment(@PathVariable("id") int id) {
        return departmentService.getDepartment(id);
    }

    @PostMapping("/departments")
    public void addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
    }

    @PutMapping("/departments")
    public void updateDepartment(@RequestBody Department department) {
        departmentService.updateDepartment(department);
    }

    @DeleteMapping("/departments/{id}")
    public void deleteDepartment(@PathVariable("id") int id) {
        departmentService.deleteDepartment(id);
    }
}
