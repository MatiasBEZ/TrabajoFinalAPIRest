package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.model.Source;
import com.trabajofinalinfo.apinoticias.service.SourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SourceDtoToEntityConverterTest {

    private SourceDtoToEntityConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new SourceDtoToEntityConverter();
    }

    @Test
    void when_given_a_sourceDto_it_must_return_a_source_entity() {
        //given
        SourceDto sourceDto = new SourceDto(
                null, "Diario Chaco", "diario-chaco", LocalDate.of(2022,8,1));
        Source source = new Source(
                null, "Diario Chaco", "diario-chaco", LocalDate.of(2022,8,1));
        //then
        assertEquals(source, underTest.toEntity(sourceDto));
    }
}