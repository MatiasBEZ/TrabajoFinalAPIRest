package com.trabajofinalinfo.apinoticias.controller;

import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/sources")
public class SourceController {

    private final SourceService sourceService;

    @Autowired
    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @PostMapping
    public ResponseEntity<?> createSource(@RequestBody @Valid SourceDto sourceDto) {
        return new ResponseEntity<>(sourceService.createSource(sourceDto), HttpStatus.CREATED);
    }

    @PutMapping
    @RequestMapping("/{sourceId}")
    public ResponseEntity<?> updateSource(@PathVariable Long sourceId, @RequestBody @Valid SourceDto sourceDto) {
        return new ResponseEntity<>(sourceService.updateSource(sourceId,sourceDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{sourceId}")
    public ResponseEntity<?> deleteSource(@PathVariable Long sourceId) {
        sourceService.deleteSource(sourceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findSources(@RequestParam(required = false) String name, Pageable pageable) {
        if (name != null) {
            return new ResponseEntity<>(sourceService.findByName(name, pageable), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(sourceService.findAll(pageable), HttpStatus.OK);
        }
    }
}
