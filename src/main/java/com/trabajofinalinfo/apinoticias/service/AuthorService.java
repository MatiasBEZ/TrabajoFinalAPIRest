package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.AuthorDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.model.Author;
import com.trabajofinalinfo.apinoticias.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorDtoToEntityConverter authorDtoToEntityConverter;
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorDtoToEntityConverter authorDtoToEntityConverter, AuthorRepository authorRepository) {
        this.authorDtoToEntityConverter = authorDtoToEntityConverter;
        this.authorRepository = authorRepository;
    }

    public Boolean createAuthor(AuthorDto authorDto) {
        Author author = authorDtoToEntityConverter.toEntity(authorDto);
        authorRepository.save(author);
        return true;
    }
}
