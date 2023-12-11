package com.java.springboot.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.springboot.entity.Department;
import com.java.springboot.entity.Employee;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentResponse {
    @JsonProperty("department_id")
    private String departmentId;
    @JsonProperty("department_name")
    private String departmentName;
    @JsonProperty("employees_list")
    private List<EmployeeResponse> employeeList;

    public DepartmentResponse(){

    }

}
