package org.example.bookslibrary.security.user;

import org.example.bookslibrary.model.User.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class AuthUser implements OAuth2User {
    private final User user;
    private final Map<String, Object> attributes;

    public AuthUser(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return user.getName();
    }

    public String getId() {
        return user.getId();
    }
}