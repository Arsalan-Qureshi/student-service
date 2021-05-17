package com.dev.studentservice;

import com.dev.studentservice.models.Course;
import com.dev.studentservice.models.Student;
import com.dev.studentservice.services.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTests {

    @Autowired
    private StudentService service;

    @MockBean
    private StudentRepository repository;

    @Test
    public void getAllStudentsTest() {
        List<Course> courses = Arrays.asList(
                new Course(1, "Object Oriented Programming"),
                new Course(2, "Software Engineering I"));
        when(repository.findAll()).thenReturn(Stream.of(
                new Student(1, "Arsalan", 23, "01-01-1997", 1, courses))
                .collect(Collectors.toList()));
        assertEquals(1, service.getAllStudents().size());
    }

    @Test
    public void getStudentTest() {
        List<Course> courses = Arrays.asList(
                new Course(1, "Object Oriented Programming"),
                new Course(2, "Software Engineering I"));
        when(repository.findById(1)).thenReturn(java.util.Optional.of(
                new Student(1, "Arsalan", 23, "01-01-1997", 1, courses)));
        assertEquals("Arsalan", service.getStudent(1).get().getFirstName());
        assertEquals(23, service.getStudent(1).get().getAge());
        assertEquals("01-01-1997", service.getStudent(1).get().getDateOfBirth());
        assertEquals(1, service.getStudent(1).get().getDepartmentId());
        assertEquals("Object Oriented Programming", service.getStudent(1).get().getCourses().get(0).getName());
        assertEquals("Software Engineering I", service.getStudent(1).get().getCourses().get(1).getName());
    }

    @Test
    public void addStudentTest() {
        List<Course> courses = Arrays.asList(
                new Course(1, "Object Oriented Programming"),
                new Course(2, "Software Engineering I"));
        Student student = new Student(1, "Arsalan", 23, "01-01-1997", 1, courses);
        service.addStudent(student);
        verify(repository, times(1)).save(student);
    }

    @Test
    public void deleteStudentTest() {
        List<Course> courses = Arrays.asList(
                new Course(1, "Object Oriented Programming"),
                new Course(2, "Software Engineering I"));
        Student student = new Student(1, "Arsalan", 23, "01-01-1997", 1, courses);
        service.deleteStudent(student.getId());
        verify(repository, times(1)).deleteById(student.getId());
    }
}
