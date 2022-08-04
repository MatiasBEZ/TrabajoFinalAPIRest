package com.trabajofinalinfo.apinoticias.controller;

import com.trabajofinalinfo.apinoticias.dto.AuthorDto;
import com.trabajofinalinfo.apinoticias.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/author")
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
    public ResponseEntity<?> findAllAuthors(Pageable pageable) {
        return new ResponseEntity<>(authorService.findAll(pageable), HttpStatus.OK);
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

    @GetMapping(value= "/search/fullname")
    public ResponseEntity<?> findByFullname(@RequestParam String fullname, Pageable pageable) {
        return new ResponseEntity<>(authorService.findByFullname(fullname, pageable), HttpStatus.OK);
    }

    @GetMapping(value= "/search/date")
    public ResponseEntity<?> findByCreatedAfterDate(@RequestParam
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate date, Pageable pageable) {
        return new ResponseEntity<>(authorService.findByCreatedAfterDate(date, pageable), HttpStatus.OK);
    }
}
