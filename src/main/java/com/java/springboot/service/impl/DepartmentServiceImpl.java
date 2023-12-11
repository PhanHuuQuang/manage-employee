package com.java.springboot.service.impl;

import com.java.springboot.entity.Department;
import com.java.springboot.entity.Employee;
import com.java.springboot.exception.DataNotFoundException;
import com.java.springboot.payload.request.DepartmentRequest;
import com.java.springboot.payload.response.ApiResponse;
import com.java.springboot.repository.DepartmentRepository;
import com.java.springboot.repository.EmployeeRepository;
import com.java.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public ApiResponse createDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setDepartmentName(departmentRequest.getDepartmentName());
        departmentRepository.save(department);
        return new ApiResponse("Create department successfully", HttpStatus.CREATED);
    }

    @Override
    public List<Department> getAllDepartment(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Department> pageResult = departmentRepository.findAll(pageable);

        if (pageResult.hasContent()){
            return pageResult.getContent();
        }
        return new ArrayList<Department>();
    }

    @Override
    public Department findDepartmentById(String departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() ->
                new DataNotFoundException("This department is not found"));
    }

    @Override
    public Department findDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName).orElseThrow(() ->
                new DataNotFoundException("This department is not found"));
    }

    @Override
    public ApiResponse addEmpDepartment(String employeeId, String departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new DataNotFoundException("Department Not Found"));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new DataNotFoundException("Employee Not Found"));
        employee.setDepartment(department);
        employeeRepository.save(employee);
        return new ApiResponse("This employee to department successfully", HttpStatus.OK);
    }

    @Override
    public ApiResponse deleteDepartment(String departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new DataNotFoundException("Department Not Found"));
        departmentRepository.delete(department);
        return new ApiResponse("Deleted department successfully", HttpStatus.OK);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ApiResponse updateDepartment(String departmentId, DepartmentRequest departmentRequest) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new DataNotFoundException("This department not found"));
        department.setDepartmentName(departmentRequest.getDepartmentName());
        departmentRepository.save(department);
        return new ApiResponse("Updated successfully", HttpStatus.OK);
    }

}
