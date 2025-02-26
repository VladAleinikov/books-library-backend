package org.example.bookslibrary.modules.User.dto.user;

public record UserResponse(
        String id,
        String name,
        String email,
        String avatarUrl
) {
}
