package org.example.bookslibrary.dto.user;

public record UserResponse(
        String id,
        String name,
        String email,
        String avatarUrl
) {
}
