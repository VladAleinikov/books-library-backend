package org.example.bookslibrary.mapper;

import org.example.bookslibrary.dto.user.UserResponse;
import org.example.bookslibrary.model.User.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
}
