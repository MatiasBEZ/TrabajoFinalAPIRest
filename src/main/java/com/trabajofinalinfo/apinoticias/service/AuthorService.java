package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.AuthorConverter;
import com.trabajofinalinfo.apinoticias.converter.AuthorDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.model.Author;
import com.trabajofinalinfo.apinoticias.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Boolean createAuthor(AuthorDto authorDto) {
        Author author = authorDtoToEntityConverter.toEntity(authorDto);
        authorRepository.save(author);
        return true;
    }

    public List<AuthorDto> findAll() {
        List<Author> authors = new ArrayList<>();
        List<AuthorDto> authorsDto = new ArrayList<>();
        authorRepository.findAll().forEach(authors::add);
        for (Author author : authors) {
            authorsDto.add(authorConverter.toDto(author));
        }
        return authorsDto;
    }
}
