package com.trabajofinalinfo.apinoticias.repository;

import com.trabajofinalinfo.apinoticias.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Page<Author> findByFullnameContaining(String fullname, Pageable pageable);
    Page<Author> findByCreatedAtAfter(LocalDate date, Pageable pageable);
}
