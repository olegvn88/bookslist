package com.petprojet.bookslist.service;

import com.petprojet.bookslist.entity.CourseEntity;
import com.petprojet.bookslist.repository.CourseRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepo repo;

    public CourseService(CourseRepo repo) {
        this.repo = repo;
    }

    public CourseEntity saveCourse(@RequestBody CourseEntity entity) {
        return repo.save(entity);
    }

    public List<CourseEntity> getCourses() {
        return repo.findAll();

    }
        public CourseEntity getCourseById(@PathVariable Long id){
            Optional<CourseEntity> course = repo.findById(id);
            if (course.isPresent()) {
                return course.get();
            }
            throw new RuntimeException("Course not found")  ;
         }
    }
