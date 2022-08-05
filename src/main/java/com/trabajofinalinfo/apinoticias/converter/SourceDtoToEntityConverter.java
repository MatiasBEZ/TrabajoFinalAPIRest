package com.trabajofinalinfo.apinoticias.converter;

import com.trabajofinalinfo.apinoticias.dto.SourceDto;
import com.trabajofinalinfo.apinoticias.model.Source;
import org.springframework.stereotype.Component;

@Component
public class SourceDtoToEntityConverter {
    public Source toEntity(SourceDto sourceDto) {
        return new Source(
                sourceDto.getId(),
                sourceDto.getName(),
                sourceDto.getCode(),
                sourceDto.getCreatedAt()
        );
    }
}
