package org.example.bookslibrary.dto.user;

public record UserSyncRequest(String oauth2Id, String name, String email, String avatarUrl) {
}
