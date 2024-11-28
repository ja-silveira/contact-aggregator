package me.zearthur.model;

import java.time.LocalDateTime;

public class Contact {

    private Long id;
    private String name;
    private String email;
    private ExternalApiSource source;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Contact(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Contact setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    public ExternalApiSource getSource() {
        return source;
    }

    public Contact setSource(ExternalApiSource source) {
        this.source = source;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Contact setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Contact setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}