package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.AuthorConverter;
import com.trabajofinalinfo.apinoticias.converter.AuthorDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.exception.IdValueNotFoundException;
import com.trabajofinalinfo.apinoticias.exception.InvalidDateException;
import com.trabajofinalinfo.apinoticias.model.Author;
import com.trabajofinalinfo.apinoticias.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorDtoToEntityConverter authorDtoToEntityConverter;
    private final AuthorConverter authorConverter;
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorDtoToEntityConverter authorDtoToEntityConverter, AuthorConverter authorConverter, AuthorRepository authorRepository) {
        this.authorDtoToEntityConverter = authorDtoToEntityConverter;
        this.authorConverter = authorConverter;
        this.authorRepository = authorRepository;
    }

    public String createAuthor(AuthorDto authorDto) {
        // Creation date validator
        LocalDate today = LocalDate.now();
        if (authorDto.getCreatedAt() == null) {
            throw new InvalidDateException("The field createdAt cannot be null!");
        } else if (authorDto.getCreatedAt().isBefore(today) ||
                authorDto.getCreatedAt().isAfter(today)) {
            throw new InvalidDateException("The creation date cannot be in the past or future!");
        }
        // convert and save
        Author author = authorDtoToEntityConverter.toEntity(authorDto);
        authorRepository.save(author);
        return "Author created successfully!";
    }

    public AuthorDto updateAuthor(Long authorId, AuthorDto authorDto) {
        // check if id exist and save
        authorRepository.findById(authorId).map(author -> {
            author.setFirstname(authorDto.getFirstname());
            author.setFullname(authorDto.getFullname());
            author.setLastname(authorDto.getLastname());
            return authorRepository.save(author);
        }).orElseThrow(() -> new IdValueNotFoundException("Author with id:"
                + authorId + " doesn't exist!"));
        // show new Dto with saved data
        Optional<Author> authorEntity = authorRepository.findById(authorId);
        AuthorDto authorResponse = authorConverter.toDto(authorEntity.get());
        return authorResponse;
    }

    public void deleteAuthor(Long authorId) {
        try {
            authorRepository.deleteById(authorId);
        }
        catch(Exception e) {
            throw new IdValueNotFoundException("Author with id:" + authorId + " doesn't exist!");
        }
    }

    public Page<AuthorDto> findAll(Pageable pageable) {
        Page<AuthorDto> authorsDto = authorRepository.findAll(pageable)
                .map(authorConverter::toDto);
        return authorsDto;
    }

    public Page<AuthorDto> findByFullname(String fullname, Pageable pageable) {
        Page<AuthorDto> authorsDto = authorRepository.findByFullnameContaining(fullname, pageable)
                .map(authorConverter::toDto);
        return authorsDto;
    }

    public Page<AuthorDto> findByCreatedAfterDate(LocalDate date, Pageable pageable) {
        Page<AuthorDto> authorsDto = authorRepository.findByCreatedAtAfter(date, pageable)
                .map(authorConverter::toDto);
        return authorsDto;
    }

    public Page<AuthorDto> findByFullnameAndDateAfter(String fullname, LocalDate date, Pageable pageable) {
        Page<AuthorDto> authorsDto = authorRepository.findByFullnameAndDateAfter(fullname, date, pageable)
                .map(authorConverter::toDto);
        return authorsDto;
    }
}
