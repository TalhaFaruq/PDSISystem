package com.tfworkers.PDSISystem.Model.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;


/**
 * The type Causeof action.
 */
@Data
@Entity
public class CauseofAction {
    @Id
    private Long causeofActionId;
    @NotBlank(message = "Name is Mandatory")
    private String name;
    @NotBlank(message = "Post is Mandatory")
    private String post;
    private String causeofChange;
    @NotBlank(message = "Actions taken are Mandatory")
    private String actions;
    private Date createdDate;
    private Date updatedDate;
    private boolean isActive = true;
}
