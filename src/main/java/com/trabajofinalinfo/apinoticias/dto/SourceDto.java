package com.trabajofinalinfo.apinoticias.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SourceDto {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String code;
    private LocalDate createdAt;
    private List<ArticleDto> articles = new ArrayList<>();

    public SourceDto(String name, String code, LocalDate createdAt, List<ArticleDto> articles) {
        this.name = name;
        this.code = code;
        this.createdAt = createdAt;
        this.articles = articles;
    }

    public SourceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<ArticleDto> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDto> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceDto sourceDto = (SourceDto) o;
        return Objects.equals(id, sourceDto.id) && Objects.equals(name, sourceDto.name) && Objects.equals(code, sourceDto.code) && Objects.equals(createdAt, sourceDto.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, createdAt);
    }

    @Override
    public String toString() {
        return "SourceDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
