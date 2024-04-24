package com.petprojet.bookslist.service;

import com.petprojet.bookslist.entity.StudentEntity;
import com.petprojet.bookslist.exception.BooksAlreadyExistsException;
import com.petprojet.bookslist.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo repo;

    public StudentService(StudentRepo repo) {
        this.repo = repo;
    }

    public StudentEntity saveStudent(StudentEntity studentEntity) {
        Optional<StudentEntity> student = repo.findByName(studentEntity.getName());
        if (student.isPresent()) {
           throw new BooksAlreadyExistsException("student name is already taken");
       }
        return repo.save(studentEntity);
    }


    public List<StudentEntity> getStudents() {
        return repo.findAll();
    }

    public StudentEntity getStudentById(Long studentId) {
        return repo.findById(studentId).orElse(null);
    }
}

