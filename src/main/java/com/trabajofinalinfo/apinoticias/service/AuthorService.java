package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.AuthorConverter;
import com.trabajofinalinfo.apinoticias.converter.AuthorDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.model.Author;
import com.trabajofinalinfo.apinoticias.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public Boolean createAuthor(AuthorDto authorDto) {
        if (authorDto.getCreatedAt() == null) {
            throw new RuntimeException("You can't give a null creation date!");
        }
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

    public AuthorDto updateAuthor(Long authorId, AuthorDto authorDto) {
        authorRepository.findById(authorId).map(author -> {
            author.setFirstname(authorDto.getFirstname());
            author.setFullname(authorDto.getFullname());
            author.setLastname(authorDto.getLastname());
            return authorRepository.save(author);
        }).orElseThrow(() -> new RuntimeException("Id not found"));

        Optional<Author> authorEntity = authorRepository.findById(authorId);
        AuthorDto authorResponse = authorConverter.toDto(authorEntity.get());

        return authorResponse;
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}
