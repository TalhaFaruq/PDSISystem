package com.tfworkers.PDSISystem.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Report {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long report_id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Name is Description")
    private String description;
}
