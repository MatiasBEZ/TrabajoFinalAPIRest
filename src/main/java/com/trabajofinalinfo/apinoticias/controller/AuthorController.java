package com.trabajofinalinfo.apinoticias.controller;

import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody @Valid AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.createAuthor(authorDto), HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("/all")
    public ResponseEntity<?> findAllAuthors() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.FOUND);
    }

    @PutMapping
    @RequestMapping("/{authorId}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long authorId, @RequestBody @Valid AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.updateAuthor(authorId,authorDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{authorId}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteAuthor(authorId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
