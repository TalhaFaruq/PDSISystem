package com.tfworkers.PDSISystem.Model.DTO;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class SelectUsersProjectDTO {
    private List<Long> User;
    private Long projectId;
    private String post;
}