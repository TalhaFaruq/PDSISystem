package com.tfworkers.PDSISystem.Model.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class CauseofAction {
    @Id
    private Long causeofActionId;
    private String name;
    private String post;
    private String causeofChange;
    private String actions;
}
