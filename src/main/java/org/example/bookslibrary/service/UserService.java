package org.example.bookslibrary.service;

import org.example.bookslibrary.dto.user.UserSyncRequest;
import org.example.bookslibrary.model.User.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    Optional<User> getUser(String userId);
    long countUsers();
    void deleteUser(String userId);
    User syncUser(UserSyncRequest userSyncRequest);
}
