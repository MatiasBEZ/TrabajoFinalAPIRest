package com.trabajofinalinfo.apinoticias.controller;

import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/source")
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

    @GetMapping
    @RequestMapping("/all")
    public ResponseEntity<?> findAllSources() {
        return new ResponseEntity<>(sourceService.findAll(), HttpStatus.OK);
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
    public ResponseEntity<?> findByName(@RequestParam String name) {
        return new ResponseEntity<>(sourceService.findByName(name), HttpStatus.OK);
    }
}
