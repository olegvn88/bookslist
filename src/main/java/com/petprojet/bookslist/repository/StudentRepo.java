package com.petprojet.bookslist.repository;


import com.petprojet.bookslist.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findById(Long id);

    Optional<StudentEntity> findByName(String name);

}

