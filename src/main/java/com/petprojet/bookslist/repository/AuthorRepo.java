package com.petprojet.bookslist.repository;

import com.petprojet.bookslist.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findById(Long id);

    Optional<AuthorEntity> findByName(String name);

    @Override
    void deleteById(Long id);
}
