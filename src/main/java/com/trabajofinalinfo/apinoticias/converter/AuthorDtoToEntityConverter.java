package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoToEntityConverter {

    private final ArticleDtoToEntityConverter articleDtoToEntityConverter;

    public AuthorDtoToEntityConverter(ArticleDtoToEntityConverter articleDtoToEntityConverter) {
        this.articleDtoToEntityConverter = articleDtoToEntityConverter;
    }

    public Author toEntity(AuthorDto authorDto) {
        return new Author(
                authorDto.getFirstname(),
                authorDto.getLastname(),
                authorDto.getFullname(),
                authorDto.getCreatedAt(),
                articleDtoToEntityConverter.toEntity(authorDto.getArticles())
        );
    }
}
