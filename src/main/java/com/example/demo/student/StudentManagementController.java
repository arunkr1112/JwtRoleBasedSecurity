package com.example.demo.student;

import com.example.demo.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    @Autowired UserDao userDao;
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

//    hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents() {
        System.out.println("getAllStudents");
        return STUDENTS;
    }

    @GetMapping("/db/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public User getDBUserDetails(@PathVariable("id")Long id) {
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isPresent())
            return userOptional.get();
        return  null;
    }

    @GetMapping("seek")
    @PreAuthorize("hasRole('ROLE_JOBSEEKER')")
    public List<Student> getAllStudentsForJobSeeker() {
        System.out.println("getAllStudents");
        return STUDENTS;
    }

    @GetMapping("a2z")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public List<Student> getAllStudentsAdministrator() {
        System.out.println("getAllStudents");
        return STUDENTS;
    }


    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("registerNewStudent");
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
        System.out.println("updateStudent");
        System.out.println(String.format("%s %s", studentId, student));
    }
}
