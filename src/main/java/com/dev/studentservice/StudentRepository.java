package com.dev.studentservice;

import com.dev.studentservice.models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Optional<Student> findByFirstName(String name);
}
