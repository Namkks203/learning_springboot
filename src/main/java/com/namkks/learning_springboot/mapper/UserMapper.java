package com.namkks.learning_springboot.mapper;

import com.namkks.learning_springboot.dto.request.UserCreationRequest;
import com.namkks.learning_springboot.dto.request.UserUpdateRequest;
import com.namkks.learning_springboot.dto.response.UserResponse;
import com.namkks.learning_springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    //@Mapping(target = "lastName", ignore = true)
    UserResponse toUserReponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
