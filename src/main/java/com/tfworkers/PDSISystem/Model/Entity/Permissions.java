package com.tfworkers.PDSISystem.Model.Entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
public class Permissions {
    @Id
    @GeneratedValue
    private long id;
    @NotBlank(message = "Name of the role is mandatory")
    @Column(unique = true, nullable = false)
    private String name;
    private boolean isActive = true;
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "updatedDate")
    private Date updatedDate;
}