package org.example.bookslibrary.modules.User.dto;

public record UserResponse(
        String id,
        String name,
        String email,
        String avatarUrl
) {
}
