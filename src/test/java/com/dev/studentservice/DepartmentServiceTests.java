package com.dev.studentservice;

import com.dev.studentservice.models.Department;
import com.dev.studentservice.services.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentServiceTests {

    @Autowired
    private DepartmentService service;

    @MockBean
    private DepartmentRepository repository;

    @Test
    public void getAllDepartmentsTest() {
        when(repository.findAll()).thenReturn(Stream.of(
                new Department(1, "Computer Science"))
                .collect(Collectors.toList()));
        assertEquals(1, service.getAllDepartments().size());
    }

    @Test
    public void getDepartmentTest() {
        when(repository.findById(1)).thenReturn(java.util.Optional.of(
                new Department(1, "Computer Science")));
        assertEquals("Computer Science", service.getDepartment(1).get().getName());
    }

    @Test
    public void addTopicTest() {
        Department department = new Department(1, "Computer Science");
        service.addDepartment(department);
        verify(repository, times(1)).save(department);
    }

    @Test
    public void deleteTopicTest() {
        Department department = new Department(1, "Computer Science");
        service.deleteDepartment(department.getId());
        verify(repository, times(1)).deleteById(department.getId());
    }
}
