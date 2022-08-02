package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    private final ArticleConverter articleConverter;

    @Autowired
    public AuthorConverter(ArticleConverter articleConverter) {
        this.articleConverter = articleConverter;
    }

    public AuthorDto toDto(Author author) {
        return new AuthorDto(
                author.getFirstname(),
                author.getLastname(),
                author.getFullname(),
                author.getCreatedAt(),
                articleConverter.toDto(author.getArticles())
        );
    }
}
