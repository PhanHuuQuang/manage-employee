package com.java.springboot.controller;


import com.java.springboot.entity.User;
import com.java.springboot.payload.request.UserRequest;
import com.java.springboot.payload.response.ApiResponse;
import com.java.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/information")
    public ResponseEntity<?> userInformation(){
        return new ResponseEntity<>(userService.userInformation(), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUser(@RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "userId") String sortBy){
        List<User> userList = userService.getAllUser(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<User>>(userList, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }

    @PutMapping("/update-user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable("userId") String userId,
                                                  @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.updateUser(userId, userRequest),HttpStatus.OK);
    }
}
