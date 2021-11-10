package com.tfworkers.PDSISystem.Model.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


/**
 * The type Causeof action.
 */
@Data
@Entity
public class CauseofAction {
    @Id
    private Long causeofActionId;
    private String name;
    private String post;
    private String causeofChange;
    private String actions;
    private Date createdDate;
    private Date updatedDate;
}
