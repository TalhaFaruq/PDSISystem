package com.tfworkers.PDSISystem.Model.Entity.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class RecommendedManagerDTO {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private int userId;
    private String name;
    private String department;




}
