package com.trabajofinalinfo.apinoticias.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class AuthorDto {
    private Long id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    private String fullname;
    private LocalDate createdAt;
    private List<ArticleDto> articles = new ArrayList<>();

    public AuthorDto(String firstname, String lastname, String fullname, LocalDate createdAt, List<ArticleDto> articles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = fullname;
        this.createdAt = createdAt;
        this.articles = articles;
    }

    public AuthorDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname = firstname + " " + lastname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
        AuthorDto authorDto = (AuthorDto) o;
        return Objects.equals(id, authorDto.id) && Objects.equals(firstname, authorDto.firstname) && Objects.equals(lastname, authorDto.lastname) && Objects.equals(fullname, authorDto.fullname) && Objects.equals(createdAt, authorDto.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, fullname, createdAt);
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
