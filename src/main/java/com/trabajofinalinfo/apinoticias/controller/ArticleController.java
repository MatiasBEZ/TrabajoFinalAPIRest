package com.trabajofinalinfo.apinoticias.controller;

import com.trabajofinalinfo.apinoticias.dto.ArticleDto;
import com.trabajofinalinfo.apinoticias.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;


@Validated
@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody @Valid ArticleDto articleDto) {
        return new ResponseEntity<>(articleService.createArticle(articleDto), HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("/all")
    public ResponseEntity<?> findAllArticles(Pageable pageable) {
        return new ResponseEntity<>(articleService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping
    @RequestMapping("/{articleId}")
    public ResponseEntity<?> updateArticle(@PathVariable Long articleId, @RequestBody @Valid ArticleDto articleDto) {
        return new ResponseEntity<>(articleService.updateArticle(articleId,articleDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value= "/search")
    public ResponseEntity<?> findByFilter(@Valid @Size(min=3) @RequestParam(required = false)
                                              String filter, Pageable pageable) {
        if (filter != null) {
            return new ResponseEntity<>(articleService.findByFilter(filter, pageable), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(articleService.findAll(pageable), HttpStatus.OK);
        }
    }
}
