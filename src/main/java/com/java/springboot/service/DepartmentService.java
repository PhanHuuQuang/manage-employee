package com.java.springboot.service;

import com.java.springboot.entity.Department;
import com.java.springboot.payload.request.DepartmentRequest;
import com.java.springboot.payload.response.ApiResponse;
import com.java.springboot.payload.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    ApiResponse createDepartment(DepartmentRequest departmentRequest);

    List<Department> getAllDepartment(Integer pageNo, Integer pageSize, String sortBy);

    Department findDepartmentById(String departmentId);

    Department findDepartmentByName(String departmentName);

    ApiResponse deleteDepartment(String departmentId);

    ApiResponse updateDepartment(String departmentId, DepartmentRequest departmentRequest);

    ApiResponse addEmpDepartment(String employeeId, String departmentId);

}
