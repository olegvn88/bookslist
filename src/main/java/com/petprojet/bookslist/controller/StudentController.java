package com.petprojet.bookslist.controller;

import com.petprojet.bookslist.entity.StudentEntity;
import com.petprojet.bookslist.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudentEntity> saveStudent(@RequestBody StudentEntity student) {
        return ResponseEntity.created(URI.create("")).body(service.saveStudent(student));
    }

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getStudents() {
        return ResponseEntity.ok(service.getStudents());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getStudentById(studentId));
    }
}
