package com.petprojet.bookslist.repository;

import com.petprojet.bookslist.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<StudentEntity, Long> {
}
