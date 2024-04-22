package com.petprojet.bookslist.repository;

import com.petprojet.bookslist.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<CourseEntity, Long> {
}
