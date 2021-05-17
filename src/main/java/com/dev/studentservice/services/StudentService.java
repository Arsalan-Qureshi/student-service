package com.dev.studentservice.services;

import com.dev.studentservice.StudentRepository;
import com.dev.studentservice.exception.ApiRequestException;
import com.dev.studentservice.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Cacheable(value = "students")
    public List<Student> getAllStudents() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        return students;
    }

    @Cacheable(value = "students")
    public Optional<Student> getStudent(int id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByName(String name) {
        return studentRepository.findByFirstName(name);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    @CacheEvict(value = "students", allEntries = true)
    public void deleteStudent(int id) {
        try {
            studentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException("Student record does not exist!");
        }
    }
}
