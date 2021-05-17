package com.dev.studentservice.models;

import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private int age;
    private String dateOfBirth;
    private int departmentId;
    @OneToMany(targetEntity = Course.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    private List<Course> courses;

    public Student() {
    }

    public Student(int id, String firstName, int age, String dateOfBirth, int departmentId, List<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.departmentId = departmentId;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
