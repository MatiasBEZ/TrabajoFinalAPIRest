package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.ArticleConverter;
import com.trabajofinalinfo.apinoticias.converter.ArticleDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.ArticleDto;
import com.trabajofinalinfo.apinoticias.model.Article;
import com.trabajofinalinfo.apinoticias.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

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

    public String createArticle(ArticleDto articleDto) {
        LocalDate today = LocalDate.now();
        if (articleDto.getPublished()) {
            if (articleDto.getPublishedAt() == null) {
                throw new RuntimeException("You need to enter a publication date!");
            }else {
                if (articleDto.getPublishedAt().isBefore(today) ||
                        articleDto.getPublishedAt().isAfter(today)) {
                    throw new RuntimeException("Invalid publication date!");
                }
            }
        } else { articleDto.setPublishedAt(null);}
        Article article = articleDtoToEntityConverter.toEntity(articleDto);
        articleRepository.save(article);
        return "Article created successfully!";
    }

    public Page<ArticleDto> findAll(Pageable pageable) {
        Page<ArticleDto> articlesDto = articleRepository.findAll(pageable)
                .map(articleConverter::toDto);
        return articlesDto;
    }

    public ArticleDto updateArticle(Long articleId, ArticleDto articleDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(RuntimeException::new);
        LocalDate today = LocalDate.now();
        if (articleDto.getPublished()) {
            if (article.getPublishedAt() == null) {
                if (articleDto.getPublishedAt() == null) {
                    throw new RuntimeException("You need to enter a publication date!");
                } else {
                    if (articleDto.getPublishedAt().isBefore(today) ||
                            articleDto.getPublishedAt().isAfter(today)) {
                        throw new RuntimeException("Invalid publication date!");
                    }
                }
            } else {
                if (articleDto.getPublishedAt() == null) {
                    throw new RuntimeException("You need to enter a publication date!");
                }
                else if (!articleDto.getPublishedAt().equals(article.getPublishedAt())) {
                    throw new RuntimeException("You can't change the publication date!");
                }
            }
        } else { articleDto.setPublishedAt(null);}
        Article articleSave = articleDtoToEntityConverter.toEntity(articleDto);
        articleSave.setId(articleId);
        articleRepository.save(articleSave);
        Optional<Article> articleEntity = articleRepository.findById(articleId);
        ArticleDto articleResponse = articleConverter.toDto(articleEntity.get());
        return articleResponse;
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    public Page<ArticleDto> findByFilter(String filter, Pageable pageable) {
        Page<ArticleDto> articlesDto = articleRepository.findByFilter(filter,pageable)
                .map(articleConverter::toDto);
        return articlesDto;
    }
}
