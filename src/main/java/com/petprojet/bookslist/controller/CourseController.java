package com.petprojet.bookslist.controller;

import com.petprojet.bookslist.entity.CourseEntity;
import com.petprojet.bookslist.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService courseService) {
        this.service = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseEntity> saveCourse(@RequestBody CourseEntity entity) {
        return ResponseEntity.ok(service.saveCourse(entity));
    }

    @GetMapping
    public ResponseEntity<List<CourseEntity>> getCourses() {
        return ResponseEntity.ok(service.getCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseEntity> getCourseById(@PathVariable Long courseId) {
        CourseEntity courseEntity = service.getCourseById(courseId);
        return ResponseEntity.ok(courseEntity);
    }
}
