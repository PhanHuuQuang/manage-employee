package com.java.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Data
@Table(name = "tbl_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @UuidGenerator
    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    @Column(name = "birthday", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "experiment", nullable = false)
    private int yearOfExp;

    @Column(name = "create_at", nullable = false)
    @CreationTimestamp //2023-11-27 00:00:00
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;
}
