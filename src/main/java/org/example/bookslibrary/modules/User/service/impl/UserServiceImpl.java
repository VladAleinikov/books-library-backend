package org.example.bookslibrary.modules.User.service.impl;

import org.example.bookslibrary.modules.User.dto.user.UserResponse;
import org.example.bookslibrary.modules.User.dto.user.UserSyncRequest;
import org.example.bookslibrary.exception.NotFoundException;
import org.example.bookslibrary.modules.User.mapper.UserMapper;
import org.example.bookslibrary.model.User.User;
import org.example.bookslibrary.model.User.UserRepository;
import org.example.bookslibrary.modules.User.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> getUsers(Pageable pageable, String searchQuery) {
        return userRepository.findUsersByNameContaining(pageable, searchQuery).map(userMapper::toResponse).toList();
    }

    @Override
    public UserResponse getUser(String userId) {
        User user = getUserEntity(userId);
        return userMapper.toResponse(user);
    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User syncUser(UserSyncRequest userSyncRequest) {
        return userRepository.findByOauth2Id(userSyncRequest.oauth2Id())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setOauth2Id(userSyncRequest.oauth2Id());
                    newUser.setName(userSyncRequest.name());
                    newUser.setEmail(userSyncRequest.email());
                    newUser.setAvatarUrl(userSyncRequest.avatarUrl());
                    return userRepository.save(newUser);
                });
    }

    private User getUserEntity(String userId) {
        return userRepository.findById(userId).orElseThrow(NotFoundException::new);
    }
}
