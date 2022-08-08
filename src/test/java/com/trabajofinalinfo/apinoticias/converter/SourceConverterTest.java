package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.model.Source;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SourceConverterTest {

    private SourceConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new SourceConverter();
    }

    @Test
    void when_given_a_entity_it_should_return_a_dto() {
        SourceDto sourceDto = new SourceDto(
                null, "Diario Chaco", "diario-chaco", LocalDate.of(2022,8,1));
        Source source = new Source(
                null, "Diario Chaco", "diario-chaco", LocalDate.of(2022,8,1));
        //then
        assertEquals(sourceDto, underTest.toDto(source));
    }
}