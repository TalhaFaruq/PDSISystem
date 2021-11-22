package com.tfworkers.PDSISystem.Model.Entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Table(name = "permissions", indexes = {
        @Index(name = "created_date_index", columnList = "createdDate"),
        @Index(name = "active_index", columnList = "isActive")
})
@Entity
@Data
public class Permissions {
    @Id
    @GeneratedValue
    private long id;
    @NotBlank(message = "Name of the permission is mandatory")
    @Column(unique = true, nullable = false)
    private String name;
    private boolean isActive = true;
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "updatedDate")
    private Date updatedDate;
}