# student-service
A rest service for managing student information.

# Project Description
A student enrolls in a department. Upon enrollment the student provides his first name, age, and date of birth. A student then selects the courses he/she wants to take.

# Operations
The service consists of the following operations:
1. Create Department
2. Delete Department
3. Enroll Student
4. Update Student Information
5. Delete Student
6. Insert Course
7. Get Student by Id
8. Get Student by Name
9. Get All Students

# Features
- Builder design pattern has been used for object creation.
- Exceptional handling has been implemented through a custom exception class.
- Swagger documentation is available. Swagger-UI can be accessed at http://localhost:8080/swagger-ui/
- Junit test cases are available for the service layer.
- Spring inbuilt caching mechanism has been used for the get operations. Cache is cleared upon a deletion call.

# Technologies
a. Spring Boot
b. Swagger
c. Java SDK 1.8.
