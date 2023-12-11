package com.java.springboot.payload.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.java.springboot.entity.Role;
import com.java.springboot.entity.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    private PasswordEncoder passwordEncoder;

    public UserResponse(User user){
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
