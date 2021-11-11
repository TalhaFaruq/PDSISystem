package com.tfworkers.PDSISystem.Model.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Roles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date CreatedDate;
    private Date UpdatedDate;
    private boolean isActive;

    @ManyToMany(targetEntity = Permissions.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Permissions> permissions = new ArrayList<>();
}
