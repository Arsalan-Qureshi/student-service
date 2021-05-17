package com.dev.studentservice;

import com.dev.studentservice.models.Course;
import com.dev.studentservice.services.CourseService;
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
public class CourseServiceTests {
    @Autowired
    private CourseService service;

    @MockBean
    private CourseRepository repository;

    @Test
    public void getAllCoursesTest() {
        when(repository.findAll()).thenReturn(Stream.of(
                new Course(1, "Introduction To Computing"))
                .collect(Collectors.toList()));
        assertEquals(1, service.getAllCourses().size());
    }

    @Test
    public void getCourseTest() {
        when(repository.findById(1)).thenReturn(java.util.Optional.of(
                new Course(1, "Introduction To Computing")));
        assertEquals("Introduction To Computing", service.getCourse(1).get().getName());
    }

    @Test
    public void addTopicTest() {
        Course course = new Course(1, "Introduction To Computing");
        service.addCourse(course);
        verify(repository, times(1)).save(course);
    }

    @Test
    public void deleteTopicTest() {
        Course course = new Course(1, "Introduction To Computing");
        service.deleteCourse(course.getId());
        verify(repository, times(1)).deleteById(course.getId());
    }
}
