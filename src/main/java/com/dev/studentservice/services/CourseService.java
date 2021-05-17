package com.dev.studentservice.services;

import com.dev.studentservice.CourseRepository;
import com.dev.studentservice.exception.ApiRequestException;
import com.dev.studentservice.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Cacheable(value = "courses")
    public List<Course> getAllCourses() {
        List<Course> courses = (List<Course>) courseRepository.findAll();
        return courses;
    }

    @Cacheable(value = "courses")
    public Optional<Course> getCourse(int id) {
        return courseRepository.findById(id);
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    @CacheEvict(value = "courses", allEntries = true)
    public void deleteCourse(int id) {
        try {
            courseRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException("Course does not exist!");
        }
    }
}
