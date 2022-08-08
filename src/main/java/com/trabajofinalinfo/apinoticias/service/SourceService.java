package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.SourceConverter;
import com.trabajofinalinfo.apinoticias.converter.SourceDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.exception.IdValueNotFoundException;
import com.trabajofinalinfo.apinoticias.exception.InvalidDateException;
import com.trabajofinalinfo.apinoticias.model.Source;
import com.trabajofinalinfo.apinoticias.repository.SourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SourceService {
    private final SourceDtoToEntityConverter sourceDtoToEntityConverter;
    private final SourceConverter sourceConverter;
    private final SourceRepository sourceRepository;

    public SourceService(SourceDtoToEntityConverter sourceDtoToEntityConverter, SourceConverter sourceConverter, SourceRepository sourceRepository) {
        this.sourceDtoToEntityConverter = sourceDtoToEntityConverter;
        this.sourceConverter = sourceConverter;
        this.sourceRepository = sourceRepository;
    }

    public String createSource(SourceDto sourceDto) {
        // Creation date validator
        LocalDate today = LocalDate.now();
        if (sourceDto.getCreatedAt() == null) {
            throw new InvalidDateException("The field createdAt cannot be null!");
        } else if (sourceDto.getCreatedAt().isBefore(today) ||
                sourceDto.getCreatedAt().isAfter(today)) {
            throw new InvalidDateException("The creation date cannot be in the past or future!");
        }
        // convert and save
        Source source = sourceDtoToEntityConverter.toEntity(sourceDto);
        sourceRepository.save(source);
        return "Source created successfully!";
    }

    public SourceDto updateSource(Long sourceId, SourceDto sourceDto) {
        // check if id exist and save
        sourceRepository.findById(sourceId).map(source -> {
            source.setName(sourceDto.getName());
            source.setCode(sourceDto.getCode());
            return sourceRepository.save(source);
        }).orElseThrow(() -> new IdValueNotFoundException("Source with id:"
                + sourceId + " doesn't exist!"));
        // show new Dto with saved data
        Optional<Source> sourceEntity = sourceRepository.findById(sourceId);
        SourceDto sourceResponse = sourceConverter.toDto(sourceEntity.get());
        return sourceResponse;
    }

    public void deleteSource(Long sourceId) {
        if (sourceRepository.findById(sourceId).isPresent()){
            sourceRepository.deleteById(sourceId);
        } else {
            throw new IdValueNotFoundException("Source with id:" + sourceId + " doesn't exist!");
        }
    }

    public Page<SourceDto> findAll(Pageable pageable) {
        Page<SourceDto> sourcesDto = sourceRepository.findAll(pageable)
                .map(sourceConverter::toDto);
        return sourcesDto;
    }

    public Page<SourceDto> findByName(String name, Pageable pageable) {
        Page<SourceDto> sourcesDto = sourceRepository.findByNameContaining(name, pageable)
                .map(sourceConverter::toDto);
        return sourcesDto;
    }
}
