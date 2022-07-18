package com.petprojet.bookslist.repository;

import com.petprojet.bookslist.entity.BookEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findById(Long id);

    Optional<BookEntity> findByName(String name);

    @Override
    void deleteById(Long aLong);
}
