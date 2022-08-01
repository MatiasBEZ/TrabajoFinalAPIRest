package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SourceConverter {

    private final ArticleConverter articleConverter;

    @Autowired
    public SourceConverter(ArticleConverter articleConverter) {
        this.articleConverter = articleConverter;
    }

    public SourceDto toDto(Source source) {
        return new SourceDto(
                source.getName(),
                source.getCode(),
                source.getCreatedAt(),
                articleConverter.toDto(source.getArticles())
        );
    }
}
