package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.SourceDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.model.Source;
import com.trabajofinalinfo.apinoticias.repository.SourceRepository;
import org.springframework.stereotype.Service;



@Service
public class SourceService {
    private final SourceDtoToEntityConverter sourceDtoToEntityConverter;
    private final SourceRepository sourceRepository;

    public SourceService(SourceDtoToEntityConverter sourceDtoToEntityConverter, SourceRepository sourceRepository) {
        this.sourceDtoToEntityConverter = sourceDtoToEntityConverter;
        this.sourceRepository = sourceRepository;
    }

    public Boolean createSource(SourceDto sourceDto) {
        Source source = sourceDtoToEntityConverter.toEntity(sourceDto);
        sourceRepository.save(source);
        return true;
    }
}
