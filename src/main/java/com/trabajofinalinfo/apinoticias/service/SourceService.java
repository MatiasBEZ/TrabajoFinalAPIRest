package com.trabajofinalinfo.apinoticias.service;

import com.trabajofinalinfo.apinoticias.converter.SourceConverter;
import com.trabajofinalinfo.apinoticias.converter.SourceDtoToEntityConverter;
import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.model.Source;
import com.trabajofinalinfo.apinoticias.repository.SourceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        Source source = sourceDtoToEntityConverter.toEntity(sourceDto);
        sourceRepository.save(source);
        return true;
    }

    public List<SourceDto> findAll() {
        List<Source> sources = new ArrayList<>();
        List<SourceDto> sourcesDto = new ArrayList<>();
        sourceRepository.findAll().forEach(sources::add);
        for (Source source : sources) {
            sourcesDto.add(sourceConverter.toDto(source));
        }
        return sourcesDto;
        //return (List<Source>) sourceRepository.findAll();
    }

    public SourceDto updateSource(Long sourceId, SourceDto sourceDto) {
        /**return sourceRepository.findById(sourceId).map(source ->
         {source.setName(sourceDto.getName());
          source.setCode(sourceDto.getCode());
          source.setCreatedAt(sourceDto.getCreatedAt());
          return sourceRepository.save(source);
         });**/
        sourceRepository.findById(sourceId).orElseThrow(RuntimeException::new);
        Source source = sourceDtoToEntityConverter.toEntity(sourceDto);
        source.setId(sourceId);
        sourceRepository.save(source);
        return sourceDto;
    }
}
