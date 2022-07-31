package com.trabajofinalinfo.apinoticias.repository;

import com.trabajofinalinfo.apinoticias.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository extends JpaRepository<Source, Long> {
}
