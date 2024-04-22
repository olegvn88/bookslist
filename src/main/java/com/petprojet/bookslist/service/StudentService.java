package com.petprojet.bookslist.service;

import com.petprojet.bookslist.entity.StudentEntity;
import com.petprojet.bookslist.repository.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepo repo;

    public StudentService(StudentRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    public StudentEntity saveStudent(@RequestBody StudentEntity student) {
        return repo.save(student);
    }

    @GetMapping
    public List<StudentEntity> getStudents() {
        return repo.findAll();
    }

    @GetMapping("/{studentId}")
    public StudentEntity getStudentById(@PathVariable Long studentId) {
        return repo.findById(studentId).orElse(null);
    }
}

