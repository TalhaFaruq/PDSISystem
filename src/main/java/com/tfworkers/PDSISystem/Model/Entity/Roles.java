package com.tfworkers.PDSISystem.Model.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Table(name = "roles", indexes = {
        @Index(name = "created_date_index", columnList = "createdDate"),
        @Index(name = "active_index", columnList = "isActive")
})
@Entity
@Data
public class Roles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is Mandatory")
    private String name;
    private Date createdDate;
    private Date updatedDate;
    private boolean isActive = true;

    @ManyToMany(targetEntity = Permissions.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Permissions> permissions = new ArrayList<>();
}
