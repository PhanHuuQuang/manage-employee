package com.java.springboot.service;

import com.java.springboot.entity.Employee;
import com.java.springboot.payload.request.EmpToDeptRequest;
import com.java.springboot.payload.request.EmployeeRequest;
import com.java.springboot.payload.response.ApiResponse;

import java.util.List;

public interface EmployeeService {
    ApiResponse createEmployee(EmployeeRequest employeeRequest);

    List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);

    Employee findEmployeeById(String employeeId);

    ApiResponse deleteEmployee(String employeeId);

    ApiResponse updateEmployee(String employeeId, EmployeeRequest employeeRequest);

    ApiResponse assignEmployee(String userId, EmployeeRequest employeeRequest);
}
