package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.ArticleConverter;
import com.trabajofinalinfo.apinoticias.converter.ArticleDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.ArticleDto;
import com.trabajofinalinfo.apinoticias.exception.IdValueNotFoundException;
import com.trabajofinalinfo.apinoticias.exception.InvalidDateException;
import com.trabajofinalinfo.apinoticias.exception.InvalidIdException;
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
        // Publication date validator
        LocalDate today = LocalDate.now();
        if (articleDto.getPublished()) {
            if (articleDto.getPublishedAt() == null) {
                throw new InvalidDateException("You need to enter a publication date!");
            }else {
                if (articleDto.getPublishedAt().isBefore(today) ||
                        articleDto.getPublishedAt().isAfter(today)) {
                    throw new InvalidDateException("The publication date cannot be in the past or future!");
                }
            }
        } else { articleDto.setPublishedAt(null);}
        // Author and Source id validator
        if (articleDto.getAuthor().getId() == null || articleDto.getSource().getId() == null) {
            throw new InvalidIdException("Source or Author ID cannot be null!");
        }
        // convert and save
        Article article = articleDtoToEntityConverter.toEntity(articleDto);
        articleRepository.save(article);
        return "Article created successfully!";
    }

    public ArticleDto updateArticle(Long articleId, ArticleDto articleDto) {
        // Check if article id exist
        Article article = articleRepository.findById(articleId).orElseThrow(()
                -> new IdValueNotFoundException("Article with id:" + articleId + " not found!"));
        // Publication date validator
        LocalDate today = LocalDate.now();
        if (articleDto.getPublished()) {
            if (article.getPublishedAt() == null) {
                if (articleDto.getPublishedAt() == null) {
                    throw new InvalidDateException("You need to enter a publication date!");
                } else {
                    if (articleDto.getPublishedAt().isBefore(today) ||
                            articleDto.getPublishedAt().isAfter(today)) {
                        throw new InvalidDateException("The publication date cannot be in the past or future!");
                    }
                }
            } else {
                if (articleDto.getPublishedAt() == null) {
                    throw new InvalidDateException("You need to enter a publication date!");
                }
                else if (!articleDto.getPublishedAt().equals(article.getPublishedAt())) {
                    throw new InvalidDateException("You can't change the publication date!");
                }
            }
        } else { articleDto.setPublishedAt(null);}
        // Author and Source id validator
        if (articleDto.getAuthor().getId() == null || articleDto.getSource().getId() == null) {
            throw new InvalidIdException("Source or Author ID cannot be null!");
        }
        // save the article
        Article articleSave = articleDtoToEntityConverter.toEntity(articleDto);
        articleSave.setId(articleId);
        articleRepository.save(articleSave);
        // show new Dto with saved data
        Optional<Article> articleEntity = articleRepository.findById(articleId);
        ArticleDto articleResponse = articleConverter.toDto(articleEntity.get());
        return articleResponse;
    }

    public void deleteArticle(Long articleId) {
        try {
            articleRepository.deleteById(articleId);
        } catch(Exception e) {
            throw new IdValueNotFoundException("Article with id:" + articleId + " doesn't exist!");
        }
    }

    public Page<ArticleDto> findAll(Pageable pageable) {
        Page<ArticleDto> articlesDto = articleRepository.findAll(pageable)
                .map(articleConverter::toDto);
        return articlesDto;
    }

    public Page<ArticleDto> findByFilter(String filter, Pageable pageable) {
        Page<ArticleDto> articlesDto = articleRepository.findByFilter(filter,pageable)
                .map(articleConverter::toDto);
        return articlesDto;
    }
}
