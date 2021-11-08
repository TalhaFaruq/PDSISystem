package com.tfworkers.PDSISystem.Model.DTO;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class OnSpotChangesDTO {
    private String name;
    private String post;
    private String causeofChange;
    private String actions;
}
