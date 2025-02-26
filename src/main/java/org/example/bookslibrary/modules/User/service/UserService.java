package org.example.bookslibrary.modules.User.service;

import org.example.bookslibrary.modules.User.dto.user.UserResponse;
import org.example.bookslibrary.modules.User.dto.user.UserSyncRequest;
import org.example.bookslibrary.model.User.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers(Pageable pageable, String searchQuery);
    UserResponse getUser(String userId);
    long countUsers();
    void deleteUser(String userId);
    User syncUser(UserSyncRequest userSyncRequest);
}
