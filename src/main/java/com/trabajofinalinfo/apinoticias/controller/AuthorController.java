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
@RequestMapping("/api/v1/authors")
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

    @GetMapping
    public ResponseEntity<?> findByFullname(@RequestParam(name = "fullname", required = false)
          String fullname, @RequestParam(name = "date", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Pageable pageable) {
        if (fullname != null && date == null) {
            return new ResponseEntity<>(authorService.findByFullname(fullname, pageable), HttpStatus.OK);
        } else if (date != null && fullname == null) {
            return new ResponseEntity<>(authorService.findByCreatedAfterDate(date, pageable), HttpStatus.OK);
        } else if (fullname != null && date != null){
            return new ResponseEntity<>(authorService.findByFullnameAndDateAfter(fullname, date, pageable),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(authorService.findAll(pageable),HttpStatus.OK);
        }
    }
}
