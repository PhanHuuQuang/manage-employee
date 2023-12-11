package com.java.springboot.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.java.springboot.entity.Employee;
import com.java.springboot.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {
    private String employeeId;
    private Date dateOfBirth;
    private Integer yearOfExp;
    private User user;
    public EmployeeResponse(){

    }
}
