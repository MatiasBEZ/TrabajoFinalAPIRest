package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.ArticleConverter;
import com.trabajofinalinfo.apinoticias.converter.ArticleDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.ArticleDto;
import com.trabajofinalinfo.apinoticias.model.Article;
import com.trabajofinalinfo.apinoticias.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleDtoToEntityConverter articleDtoToEntityConverter;
    private final ArticleConverter articleConverter;
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleDtoToEntityConverter articleDtoToEntityConverter, ArticleConverter articleConverter, ArticleRepository articleRepository) {
        this.articleDtoToEntityConverter = articleDtoToEntityConverter;
        this.articleConverter = articleConverter;
        this.articleRepository = articleRepository;
    }

    public Article createArticle(ArticleDto articleDto) {
        Article article = articleDtoToEntityConverter.toEntity(articleDto);
        articleRepository.save(article);
        return article;
    }

    public List<ArticleDto> findAll() {
        List<Article> articles = new ArrayList<>();
        List<ArticleDto> articlesDto = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        for (Article article : articles) {
            articlesDto.add(articleConverter.toDto(article));
        }
        return articlesDto;
        //return (List<Article>) articleRepository.findAll();
    }
}
