package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.AuthorConverter;
import com.trabajofinalinfo.apinoticias.converter.AuthorDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.exception.IdValueNotFoundException;
import com.trabajofinalinfo.apinoticias.exception.InvalidDateException;
import com.trabajofinalinfo.apinoticias.model.Author;
import com.trabajofinalinfo.apinoticias.repository.AuthorRepository;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    private AuthorService underTest;
    @Mock
    private AuthorDtoToEntityConverter authorDtoToEntityConverter;
    @Mock
    private AuthorConverter authorConverter;


    @BeforeEach
    void setUp() {
        underTest = new AuthorService(authorDtoToEntityConverter, authorConverter, authorRepository);
    }

    @Test
    void an_exception_should_be_called_when_createdAt_is_in_the_pass() {
        //given
        LocalDate createdAt = LocalDate.of(2000,8,3);
        AuthorDto authorDto = new AuthorDto(null, "Roberto", "Carlos", null, createdAt);
        //then
        assertThatThrownBy(() -> underTest.createAuthor(authorDto))
                .isInstanceOf(InvalidDateException.class)
                .hasMessageContaining("The creation date cannot be in the past or future!");
    }

    @Test
    void an_exception_should_be_called_when_createdAt_is_in_the_future() {
        //given
        LocalDate createdAt = LocalDate.of(2030,8,3);
        AuthorDto authorDto = new AuthorDto(null, "Roberto", "Carlos", null, createdAt);
        //then
        assertThatThrownBy(() -> underTest.createAuthor(authorDto))
                .isInstanceOf(InvalidDateException.class)
                .hasMessageContaining("The creation date cannot be in the past or future!");
    }

    @Test
    void an_exception_should_be_called_when_createdAt_is_null() {
        //given
        AuthorDto authorDto = new AuthorDto(null, "Roberto", "Carlos", null, null);
        //then
        assertThatThrownBy(() -> underTest.createAuthor(authorDto))
                .isInstanceOf(InvalidDateException.class)
                .hasMessageContaining("The field createdAt cannot be null!");
    }

    @Test
    void given_a_valid_dto_the_method_should_return_success_message() {
        //given
        LocalDate createdAt = LocalDate.now();
        AuthorDto authorDto = new AuthorDto(null, "Roberto", "Carlos", null, createdAt);
        Author author = new Author(null, "Roberto", "Carlos", null, createdAt);
        given(authorDtoToEntityConverter.toEntity(authorDto)).willReturn(author);
        String expected = "Author created successfully!";
        //then
        assertEquals(expected, underTest.createAuthor(authorDto));
    }

    @Test
    void given_a_valid_dto_the_author_being_save_must_be_equals_to_the_provided() {
        //given
        LocalDate createdAt = LocalDate.now();
        AuthorDto authorDto = new AuthorDto(null, "Roberto", "Carlos", null, createdAt);
        Author author = new Author(null, "Roberto", "Carlos", null, createdAt);
        given(authorDtoToEntityConverter.toEntity(authorDto)).willReturn(author);
        //then
        underTest.createAuthor(authorDto);
        ArgumentCaptor<Author> authorArgumentCaptor = ArgumentCaptor.forClass(Author.class);
        verify(authorRepository).save(authorArgumentCaptor.capture());
        Author capturedAuthor = authorArgumentCaptor.getValue();
        assertThat(capturedAuthor).isEqualTo(author);
    }

    @Test
    void verify_that_when_an_author_exist_then_deleteById_is_called() {
        //given
        Long authorId = 45L;
        LocalDate createdAt = LocalDate.of(2022,8,1);
        Author author = new Author(9L, "Roberto", "Carlos", "Roberto Carlos", createdAt);
        //when
        given(authorRepository.findById(authorId)).willReturn(Optional.of(author));
        underTest.deleteAuthor(authorId);
        //then
        verify(authorRepository).deleteById(authorId);
    }

    @Test
    void when_authorId_does_not_exist_then_an_exception_is_called() {
        //given
        Long authorId = 48L;
        //then
        assertThatThrownBy(() -> underTest.deleteAuthor(authorId))
                .isInstanceOf(IdValueNotFoundException.class)
                .hasMessageContaining("Author with id:" + authorId + " doesn't exist!");
    }
}