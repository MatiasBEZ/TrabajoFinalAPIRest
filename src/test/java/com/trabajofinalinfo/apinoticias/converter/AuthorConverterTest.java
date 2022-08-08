package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AuthorConverterTest {

    private AuthorConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new AuthorConverter();
    }

    @Test
    void when_given_a_entity_it_should_return_a_dto() {
        //given
        AuthorDto authorDto = new AuthorDto(null, "Roberto", "Carlos", "Roberto Carlos", LocalDate.of(2022,8,1));
        Author author = new Author(null, "Roberto", "Carlos", "Roberto Carlos", LocalDate.of(2022,8,1));
        //then
        assertEquals(authorDto, underTest.toDto(author));
    }

}