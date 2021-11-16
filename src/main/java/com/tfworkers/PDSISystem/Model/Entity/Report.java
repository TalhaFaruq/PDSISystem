package com.tfworkers.PDSISystem.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Table(name = "report", indexes = {
        @Index(name = "created_date_index", columnList = "createdDate"),
        @Index(name = "active_index", columnList = "isActive")
})
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
    private Date createdDate;
    private Date updatedDate;
    private boolean isActive = true;
}
