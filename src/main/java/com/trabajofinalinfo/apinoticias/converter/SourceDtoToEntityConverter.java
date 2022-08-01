package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.model.Source;
import org.springframework.stereotype.Component;

@Component
public class SourceDtoToEntityConverter {

    private final ArticleDtoToEntityConverter articleDtoToEntityConverter;

    public SourceDtoToEntityConverter(ArticleDtoToEntityConverter articleDtoToEntityConverter) {
        this.articleDtoToEntityConverter = articleDtoToEntityConverter;
    }

    public Source toEntity(SourceDto sourceDto) {
        return new Source(
                sourceDto.getName(),
                sourceDto.getCode(),
                sourceDto.getCreatedAt(),
                articleDtoToEntityConverter.toEntity(sourceDto.getArticles())
        );
    }
}
