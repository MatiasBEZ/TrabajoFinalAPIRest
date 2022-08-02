package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.ArticleDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.ArticleDto;
import com.trabajofinalinfo.apinoticias.model.Article;
import com.trabajofinalinfo.apinoticias.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleDtoToEntityConverter articleDtoToEntityConverter;
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleDtoToEntityConverter articleDtoToEntityConverter, ArticleRepository articleRepository) {
        this.articleDtoToEntityConverter = articleDtoToEntityConverter;
        this.articleRepository = articleRepository;
    }

    public Article createArticle(ArticleDto articleDto) {
        Article article = articleDtoToEntityConverter.toEntity(articleDto);
        articleRepository.save(article);
        return article;
    }
}
