package org.example.bookslibrary.service.impl;

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

//    @Override
//    public String addUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if(authentication instanceof OAuth2AuthenticationToken oauthToken){
//            OAuth2User oAuth2User = oauthToken.getPrincipal();
//
//            if(oAuth2User != null){
//                Map<String, Object> attributes = oAuth2User.getAttributes();
//                User user = new User();
//                user.setName((String) attributes.get("name"));
//                user.setEmail((String) attributes.get("email"));
//                user.setAvatarUrl((String) attributes.get("picture"));
//
//                return  user.toString();
//            }
//        }
//
//        return "No OAuth2AuthenticationToken found";
//    }

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
}
