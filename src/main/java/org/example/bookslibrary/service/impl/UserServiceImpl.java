package org.example.bookslibrary.service.impl;

import org.example.bookslibrary.dto.user.UserSyncRequest;
import org.example.bookslibrary.model.User.User;
import org.example.bookslibrary.model.User.UserRepository;
import org.example.bookslibrary.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(String userId) {
        return userRepository.findById(userId);
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
                .orElseGet(()->{
                    User newUser = new User();
                    newUser.setOauth2Id(userSyncRequest.oauth2Id());
                    newUser.setName(userSyncRequest.name());
                    newUser.setEmail(userSyncRequest.email());
                    newUser.setAvatarUrl(userSyncRequest.avatarUrl());
                    return userRepository.save(newUser);
                });
    }
}
