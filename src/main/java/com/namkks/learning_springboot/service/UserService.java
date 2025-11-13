package com.namkks.learning_springboot.service;

import com.namkks.learning_springboot.dto.request.UserCreationRequest;
import com.namkks.learning_springboot.dto.request.UserUpdateRequest;
import com.namkks.learning_springboot.dto.response.UserResponse;
import com.namkks.learning_springboot.entity.User;
import com.namkks.learning_springboot.exception.AppException;
import com.namkks.learning_springboot.exception.ErrorCode;
import com.namkks.learning_springboot.mapper.UserMapper;
import com.namkks.learning_springboot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor// có thể thay thế Autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);

        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserResponse getUser(String id){
        return userMapper.toUserReponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User is not found!")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User is not found!"));

        userMapper.updateUser(user, request);
        return userMapper.toUserReponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
