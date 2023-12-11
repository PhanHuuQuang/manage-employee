package com.java.springboot.controller;

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.java.springboot.entity.Department;
import com.java.springboot.entity.Employee;
import com.java.springboot.exception.DataNotFoundException;
import com.java.springboot.payload.request.DepartmentRequest;
import com.java.springboot.payload.request.EmpToDeptRequest;
import com.java.springboot.payload.response.ApiResponse;
import com.java.springboot.payload.response.DepartmentResponse;
import com.java.springboot.payload.response.EmployeeResponse;
import com.java.springboot.repository.DepartmentRepository;
import com.java.springboot.repository.EmployeeRepository;
import com.java.springboot.service.DepartmentService;
import com.java.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/create-department")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentRequest departmentRequest){
        return new ResponseEntity<>(departmentService.createDepartment(departmentRequest), HttpStatus.CREATED);
    }

    @GetMapping("/by-dept-name/{departmentName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getDepartmentByName(@PathVariable("departmentName") String departmentName){
        return new ResponseEntity<>(departmentService.findDepartmentByName(departmentName), HttpStatus.OK);
    }

    @GetMapping("/by-dept-id/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getDepartmentById(@PathVariable("departmentId") String departmentId){
        return new ResponseEntity<Department>(departmentService.findDepartmentById(departmentId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Department>> getAllDepartments(@RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                                          @RequestParam(defaultValue = "departmentId") String sortBy) {
        List<Department> departmentsList = departmentService.getAllDepartment(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Department>>(departmentsList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/emp-to-dept")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> addEmpToDepartment(@RequestBody EmpToDeptRequest empToDeptRequest){
        return new ResponseEntity<>(departmentService.addEmpDepartment(empToDeptRequest.getEmployeeId(), empToDeptRequest.getDepartmentId()), HttpStatus.OK);
    }

    @DeleteMapping("/delete-dept/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable("departmentId") String departmentId){
        return new ResponseEntity<>(departmentService.deleteDepartment(departmentId), HttpStatus.OK);
    }

    @PutMapping("/update-dept/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateDepartment(@PathVariable("departmentId") String departmentId,
                                                        @RequestBody DepartmentRequest departmentRequest){
        return new ResponseEntity<>(departmentService.updateDepartment(departmentId, departmentRequest), HttpStatus.OK);
    }

}
