package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.SourceConverter;
import com.trabajofinalinfo.apinoticias.converter.SourceDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.exception.IdValueNotFoundException;
import com.trabajofinalinfo.apinoticias.exception.InvalidDateException;
import com.trabajofinalinfo.apinoticias.model.Source;
import com.trabajofinalinfo.apinoticias.repository.SourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SourceServiceTest {
    @Mock
    private SourceRepository sourceRepository;
    private SourceService underTest;
    @Mock
    private SourceDtoToEntityConverter sourceDtoToEntityConverter;

    private SourceConverter sourceConverter;

    @BeforeEach
    void setUp() {
        underTest = new SourceService(sourceDtoToEntityConverter, sourceConverter, sourceRepository);
    }

    @Test
    void an_exception_should_be_called_when_createdAt_is_in_the_pass() {
        //given
        LocalDate createdAt = LocalDate.of(2000,8,3);
        SourceDto sourceDto = new SourceDto(null,"Clarin","clarin",createdAt);
        //then
        assertThatThrownBy(() -> underTest.createSource(sourceDto))
                .isInstanceOf(InvalidDateException.class)
                .hasMessageContaining("The creation date cannot be in the past or future!");
    }

    @Test
    void an_exception_should_be_called_when_createdAt_is_in_the_future() {
        //given
        LocalDate createdAt = LocalDate.of(2030,8,3);
        SourceDto sourceDto = new SourceDto(null,"Clarin","clarin",createdAt);
        //then
        assertThatThrownBy(() -> underTest.createSource(sourceDto))
                .isInstanceOf(InvalidDateException.class)
                .hasMessageContaining("The creation date cannot be in the past or future!");
    }

    @Test
    void an_exception_should_be_called_when_createdAt_is_null() {
        //given
        SourceDto sourceDto = new SourceDto(null,"Clarin","clarin",null);
        //then
        assertThatThrownBy(() -> underTest.createSource(sourceDto))
                .isInstanceOf(InvalidDateException.class)
                .hasMessageContaining("The field createdAt cannot be null!");
    }

    @Test
    void given_a_valid_dto_the_method_should_return_success_message() {
        //given
        LocalDate createdAt = LocalDate.now();
        SourceDto sourceDto = new SourceDto(null,"Clarin","clarin",createdAt);
        Source source = new Source(null,"Clarin","clarin",createdAt);
        given(sourceDtoToEntityConverter.toEntity(sourceDto)).willReturn(source);
        String expected = "Source created successfully!";
        //then
        assertEquals(expected, underTest.createSource(sourceDto));
    }

    @Test
    void given_a_valid_dto_the_source_being_save_must_be_equals_to_the_provided() {
        //given
        LocalDate createdAt = LocalDate.now();
        SourceDto sourceDto = new SourceDto(null,"Clarin","clarin",createdAt);
        Source source = new Source(null,"Clarin","clarin",createdAt);
        given(sourceDtoToEntityConverter.toEntity(sourceDto)).willReturn(source);
        //then
        underTest.createSource(sourceDto);
        ArgumentCaptor<Source> sourceArgumentCaptor = ArgumentCaptor.forClass(Source.class);
        verify(sourceRepository).save(sourceArgumentCaptor.capture());
        Source capturedSource = sourceArgumentCaptor.getValue();
        assertThat(capturedSource).isEqualTo(source);
    }

    @Test
    void verify_that_when_a_source_exist_then_deleteById_is_called() {
        //given
        Long sourceId = 45L;
        LocalDate createdAt = LocalDate.of(2022,8,1);
        Source source = new Source(45L,"Clarin","clarin",createdAt);
        //when
        given(sourceRepository.findById(sourceId)).willReturn(Optional.of(source));
        underTest.deleteSource(sourceId);
        //then
        verify(sourceRepository).deleteById(sourceId);
    }

    @Test
    void when_sourceId_does_not_exist_then_an_exception_is_called() {
        //given
        Long sourceId = 48L;
        //then
        assertThatThrownBy(() -> underTest.deleteSource(sourceId))
                .isInstanceOf(IdValueNotFoundException.class)
                .hasMessageContaining("Source with id:" + sourceId + " doesn't exist!");
    }
}