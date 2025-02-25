package org.example.bookslibrary.service;

import org.example.bookslibrary.model.User.User;
import org.example.bookslibrary.model.User.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2User oauth2User = super.loadUser(userRequest);

        String oauth2Id = oauth2User.getAttribute("id").toString();
        String username = oauth2User.getAttribute("login");
        String email = oauth2User.getAttribute("email");
        String avatarUrl = oauth2User.getAttribute("picture");

        User user = userRepository.findByOauth2Id(oauth2Id)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setOauth2Id(oauth2Id);
                    newUser.setName(username);
                    newUser.setEmail(email);
                    newUser.setAvatarUrl(avatarUrl);
                    return userRepository.save(newUser);
                });

        return oauth2User;
    }
}
