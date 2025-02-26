package org.example.bookslibrary.security.service;

import org.example.bookslibrary.dto.user.UserSyncRequest;
import org.example.bookslibrary.model.User.User;
import org.example.bookslibrary.security.user.AuthUser;
import org.example.bookslibrary.service.UserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("Загрузка пользователя из OAuth2 провайдера: " + userRequest.getClientRegistration().getRegistrationId());
        OAuth2User oauth2User = super.loadUser(userRequest);
        System.out.println("Данные пользователя: " + oauth2User.getAttributes());

        String oauth2Id = oauth2User.getAttribute("sub").toString();
        String name = oauth2User.getAttribute("name");
        String email = oauth2User.getAttribute("email");
        String avatarUrl = oauth2User.getAttribute("picture");

        User user = userService.syncUser(
                new UserSyncRequest(
                        oauth2Id,
                        name,
                        email,
                        avatarUrl
                ));

        return new AuthUser(user, oauth2User.getAttributes());
    }


}
