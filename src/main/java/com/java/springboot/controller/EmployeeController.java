package com.java.springboot.controller;

import com.java.springboot.entity.Employee;
import com.java.springboot.payload.request.EmpToDeptRequest;
import com.java.springboot.payload.request.EmployeeRequest;
import com.java.springboot.payload.response.ApiResponse;
import com.java.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create-employee")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.createEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                                          @RequestParam(defaultValue = "employeeId") String sortBy){
        List<Employee> employeeList = employeeService.getAllEmployees(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Employee>>(employeeList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") String employeeId){
        return new ResponseEntity<Employee>(employeeService.findEmployeeById(employeeId), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable("employeeId") String employeeId){
        return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
    }

    @PutMapping("/update-emp/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable("employeeId") String employeeId,
                                                      @RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.updateEmployee(employeeId, employeeRequest), HttpStatus.OK);
    }

    @PostMapping("/assign-emp/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> assignEmployee(@PathVariable("userId") String userId,
                                                      @RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.assignEmployee(userId, employeeRequest), HttpStatus.OK);
    }
}
