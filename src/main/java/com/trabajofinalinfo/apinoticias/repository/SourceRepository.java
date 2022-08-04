package com.trabajofinalinfo.apinoticias.repository;

import com.trabajofinalinfo.apinoticias.model.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {
    Page<Source> findByNameContaining(String name, Pageable pageable);
}
