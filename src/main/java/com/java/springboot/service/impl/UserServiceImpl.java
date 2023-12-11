package com.java.springboot.service.impl;

import com.java.springboot.entity.Employee;
import com.java.springboot.entity.User;
import com.java.springboot.exception.DataNotFoundException;
import com.java.springboot.payload.request.UserRequest;
import com.java.springboot.payload.response.ApiResponse;
import com.java.springboot.payload.response.UserResponse;
import com.java.springboot.repository.UserRepository;
import com.java.springboot.service.UserService;
import com.java.springboot.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserResponse userInformation() {
        User user = userRepository.findByEmail(SecurityUtils.getEmail()).orElseThrow(() ->
                new DataNotFoundException("Not Found"));
        if(Objects.isNull(user)){
            throw new DataNotFoundException("Cant find this user");
        }
        return new UserResponse(user);
    }

    @Override
    public List<User> getAllUser(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<User> pageResult = userRepository.findAll(pageable);

        if (pageResult.hasContent()){
            return pageResult.getContent();
        }
        return new ArrayList<User>();
    }

    @Override
    public ApiResponse deleteUser(String userId) {
        User user = userRepository.findByUserId(userId);
        userRepository.delete(user);
        return new ApiResponse("Deleted User successfully", HttpStatus.OK);
    }

    @Override
    public ApiResponse updateUser(String userId, UserRequest userRequest) {
        User user = userRepository.findByUserId(userId);
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        return new ApiResponse("Updated successfully", HttpStatus.OK);
    }
}
