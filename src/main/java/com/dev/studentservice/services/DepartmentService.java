package com.dev.studentservice.services;

import com.dev.studentservice.DepartmentRepository;
import com.dev.studentservice.exception.ApiRequestException;
import com.dev.studentservice.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Cacheable(value = "departments")
    public List<Department> getAllDepartments() {
        List<Department> departments = (List<Department>) departmentRepository.findAll();
        return departments;
    }

    @Cacheable(value = "departments")
    public Optional<Department> getDepartment(int id) {
        return departmentRepository.findById(id);
    }

    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void updateDepartment(Department department) {
        departmentRepository.save(department);
    }

    @CacheEvict(value = "departments", allEntries = true)
    public void deleteDepartment(int id) {
        try {
            departmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException("Department does not exist!");
        }
    }
}
