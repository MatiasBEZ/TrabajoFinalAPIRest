package com.trabajofinalinfo.apinoticias.repository;

import com.trabajofinalinfo.apinoticias.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
