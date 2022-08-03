package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoToEntityConverter {

    public Author toEntity(AuthorDto authorDto) {
        return new Author(
                authorDto.getId(),
                authorDto.getFirstname(),
                authorDto.getLastname(),
                authorDto.getFullname(),
                authorDto.getCreatedAt()
        );
    }
}
