package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.ArticleDto;
import com.trabajofinalinfo.apinoticias.model.Article;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleConverter {

    private final AuthorConverter authorConverter;
    private final SourceConverter sourceconverter;

    public ArticleConverter(AuthorConverter authorConverter, SourceConverter sourceconverter) {
        this.authorConverter = authorConverter;
        this.sourceconverter = sourceconverter;
    }

    public List<ArticleDto> toDto(List<Article> articles) {
        return articles.stream().map( article -> toDto(article))
                .collect(Collectors.toList());
    }

    public ArticleDto toDto(Article article) {
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getDescription(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getPublished(),
                article.getPublishedAt(),
                article.getContent(),
                authorConverter.toDto(article.getAuthor()),
                sourceconverter.toDto(article.getSource())
        );
    }
}
