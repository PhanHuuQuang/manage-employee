package com.java.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum Role {
    USER,ADMIN,EMPLOYEE
}
