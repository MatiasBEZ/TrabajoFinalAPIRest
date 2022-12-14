package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.ArticleDto;
import com.trabajofinalinfo.apinoticias.model.Article;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleDtoToEntityConverter {

    private final AuthorDtoToEntityConverter authorDtoToEntityConverter;
    private final SourceDtoToEntityConverter sourceDtoToEntityConverter;

    public ArticleDtoToEntityConverter(AuthorDtoToEntityConverter authorDtoToEntityConverter, SourceDtoToEntityConverter sourceDtoToEntityConverter) {
        this.authorDtoToEntityConverter = authorDtoToEntityConverter;
        this.sourceDtoToEntityConverter = sourceDtoToEntityConverter;
    }

    public List<Article> toEntity(List<ArticleDto> articlesDto) {
        return articlesDto.stream().map( articleDto -> toEntity(articleDto))
                .collect(Collectors.toList());
    }

    public Article toEntity(ArticleDto articleDto) {
        return new Article(
                articleDto.getTitle(),
                articleDto.getDescription(),
                articleDto.getUrl(),
                articleDto.getUrlToImage(),
                articleDto.getPublished(),
                articleDto.getPublishedAt(),
                articleDto.getContent(),
                authorDtoToEntityConverter.toEntity(articleDto.getAuthor()),
                sourceDtoToEntityConverter.toEntity(articleDto.getSource())
        );
    }
}
