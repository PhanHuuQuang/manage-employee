package com.java.springboot.service.impl;

import com.java.springboot.entity.Department;
import com.java.springboot.entity.Employee;
import com.java.springboot.entity.Role;
import com.java.springboot.entity.User;
import com.java.springboot.exception.DataExistException;
import com.java.springboot.exception.DataNotFoundException;
import com.java.springboot.payload.request.EmpToDeptRequest;
import com.java.springboot.payload.request.EmployeeRequest;
import com.java.springboot.payload.response.ApiResponse;
import com.java.springboot.repository.DepartmentRepository;
import com.java.springboot.repository.EmployeeRepository;
import com.java.springboot.repository.UserRepository;
import com.java.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ApiResponse createEmployee(EmployeeRequest employeeRequest) {
        String email = employeeRequest.getUser().getEmail();
        if(userRepository.existsByEmail(email)){
            throw new  DataExistException("This email is already exist");
        }
        else{
            var user = User.builder()
                    .firstName(employeeRequest.getUser().getFirstName())
                    .lastName(employeeRequest.getUser().getLastName())
                    .email(employeeRequest.getUser().getEmail())
                    .password(passwordEncoder.encode(employeeRequest.getUser().getPassword()))
                    .role(Role.EMPLOYEE)
                    .build();
            userRepository.save(user);

            Employee employee = new Employee();
            employee.setDateOfBirth(employeeRequest.getDateOfBirth());
            employee.setYearOfExp(employeeRequest.getYearOfExp());
            employee.setUser(user);
            employeeRepository.save(employee);
        }
        return new ApiResponse("Create employee successfully", HttpStatus.CREATED);
    }


    @Override
    public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employee> pageResult = employeeRepository.findAll(pageable);

        if (pageResult.hasContent()){
            return pageResult.getContent();
        }
        return new ArrayList<Employee>();
    }

    @Override
    public Employee findEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() ->
                new DataNotFoundException("This employee is not found"));
    }

    @Override
    public ApiResponse deleteEmployee(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new RuntimeException("Not Found"));
        User user = userRepository.findByUserId(employee.getUser().getUserId());
        employeeRepository.deleteById(employeeId);
        user.setRole(Role.USER);
        userRepository.save(user);
        return new ApiResponse("Deleted Employee successfully", HttpStatus.OK);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ApiResponse updateEmployee(String employeeId, EmployeeRequest employeeRequest) {
        Date updateDate = java.sql.Date.valueOf(LocalDate.now());
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new DataNotFoundException("This employee is not found"));
        User user = userRepository.findByUserId(employee.getUser().getUserId());
        user.setFirstName(employeeRequest.getUser().getFirstName());
        user.setLastName(employeeRequest.getUser().getLastName());
        user.setEmail(employeeRequest.getUser().getEmail());
        user.setPassword(passwordEncoder.encode(employeeRequest.getUser().getPassword()));
        userRepository.save(user);

        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setUpdateAt(updateDate);
        employee.setYearOfExp(employeeRequest.getYearOfExp());
        employeeRepository.save(employee);
        return new ApiResponse("Update successfully", HttpStatus.OK);
    }

    @Override
    public ApiResponse assignEmployee(String userId, EmployeeRequest employeeRequest) {
        User user = userRepository.findByUserId(userId);
        if(user == null){
            return new ApiResponse("Not found this user for assign", HttpStatus.NOT_FOUND);
        }
        user.setRole(Role.EMPLOYEE);
        userRepository.save(user);
        Employee employee = new Employee();
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setYearOfExp(employeeRequest.getYearOfExp());
        employee.setUser(user);
        employeeRepository.save(employee);
        return new ApiResponse("Assign successfully", HttpStatus.OK);
    }
}
