package com.java.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_department")
public class Department {
    @Id
    @UuidGenerator
    @Column(name = "department_id", nullable = false)
    private String departmentId;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> listEmployee = new ArrayList<>();
}
