package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDtoToEntityConverterTest {

    private AuthorDtoToEntityConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new AuthorDtoToEntityConverter();
    }

    @Test
    void when_given_an_authorDto_it_should_return_an_author_entity() {
        //given
        AuthorDto authorDto = new AuthorDto(null, "Roberto", "Carlos", "Roberto Carlos", LocalDate.of(2022,8,1));
        Author author = new Author(null, "Roberto", "Carlos", "Roberto Carlos", LocalDate.of(2022,8,1));
        //then
        assertEquals(author, underTest.toEntity(authorDto));
    }
}