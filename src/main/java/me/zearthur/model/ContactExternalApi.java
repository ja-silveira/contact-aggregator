package me.zearthur.model;

import java.time.LocalDateTime;

public record ContactExternalApi(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }
