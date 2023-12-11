package com.java.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.springboot.entity.Employee;
import com.java.springboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String employeeId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateAt;
    private Integer yearOfExp;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private UserDto userDto;

    public EmployeeDto(Employee employee){
        this.employeeId = employee.getEmployeeId();
        this.dateOfBirth = employee.getDateOfBirth();
        this.yearOfExp = employee.getYearOfExp();
        this.createAt = employee.getCreateAt();
        this.updateAt = employee.getUpdateAt();

        UserDto user = new UserDto();
        user.setUserId(employee.getUser().getUserId());
    }
}
