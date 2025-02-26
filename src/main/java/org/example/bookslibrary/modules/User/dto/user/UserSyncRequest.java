package org.example.bookslibrary.modules.User.dto.user;

public record UserSyncRequest(String oauth2Id, String name, String email, String avatarUrl) {
}
