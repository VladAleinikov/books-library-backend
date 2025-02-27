package org.example.bookslibrary.modules.User.mapper;

import org.example.bookslibrary.modules.User.dto.UserResponse;
import org.example.bookslibrary.model.User.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
}
