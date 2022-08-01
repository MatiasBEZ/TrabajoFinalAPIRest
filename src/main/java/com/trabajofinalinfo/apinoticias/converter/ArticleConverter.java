package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.ArticleDto;
import com.trabajofinalinfo.apinoticias.model.Article;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleConverter {
    public List<ArticleDto> toDto(List<Article> articles) {
        return articles.stream().map( article -> toDto(article))
                .collect(Collectors.toList());
    }

    public ArticleDto toDto(Article article) {
        return new ArticleDto(
                article.getTitle(),
                article.getDescription(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getPublished(),
                article.getPublishedAt(),
                article.getContent(),
                article.getAuthor(),
                article.getSource()
        );
    }
}
