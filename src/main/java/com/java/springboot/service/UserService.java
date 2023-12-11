package com.java.springboot.service;

import com.java.springboot.entity.User;
import com.java.springboot.payload.request.UserRequest;
import com.java.springboot.payload.response.ApiResponse;
import com.java.springboot.payload.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse userInformation();

    List<User> getAllUser(Integer pageNo, Integer pageSize, String sortBy);

    ApiResponse deleteUser(String userId);

    ApiResponse updateUser(String userId, UserRequest userRequest);
}
