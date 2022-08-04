package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.SourceConverter;
import com.trabajofinalinfo.apinoticias.converter.SourceDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.model.Source;
import com.trabajofinalinfo.apinoticias.repository.SourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Boolean createSource(SourceDto sourceDto) {
        if (sourceDto.getCreatedAt() == null) {
            throw new RuntimeException("You can't give a null creation date!");
        }
        Source source = sourceDtoToEntityConverter.toEntity(sourceDto);
        sourceRepository.save(source);
        return true;
    }

    public Page<SourceDto> findAll(Pageable pageable) {
        Page<SourceDto> sourcesDto = sourceRepository.findAll(pageable)
                .map(sourceConverter::toDto);
        return sourcesDto;
    }

    public SourceDto updateSource(Long sourceId, SourceDto sourceDto) {
        sourceRepository.findById(sourceId).map(source -> {
            source.setName(sourceDto.getName());
            source.setCode(sourceDto.getCode());
            return sourceRepository.save(source);
        }).orElseThrow(() -> new RuntimeException("Id not found"));

        Optional<Source> sourceEntity = sourceRepository.findById(sourceId);
        SourceDto sourceResponse = sourceConverter.toDto(sourceEntity.get());

        return sourceResponse;
    }

    public void deleteSource(Long sourceId) {
        sourceRepository.deleteById(sourceId);
    }

    public Page<SourceDto> findByName(String name, Pageable pageable) {
        Page<SourceDto> sourcesDto = sourceRepository.findByNameContaining(name, pageable)
                .map(sourceConverter::toDto);
        return sourcesDto;
    }
}
