package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.model.Source;
import org.springframework.stereotype.Component;

@Component
public class SourceConverter {
    public SourceDto toDto(Source source) {
        return new SourceDto(
                source.getId(),
                source.getName(),
                source.getCode(),
                source.getCreatedAt()
        );
    }
}
