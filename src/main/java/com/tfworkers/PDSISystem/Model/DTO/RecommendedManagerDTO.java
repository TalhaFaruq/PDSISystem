package com.tfworkers.PDSISystem.Model.DTO;


import lombok.Data;

@Data
public class RecommendedManagerDTO {

    private Long id;
    private String first_name;
    private String last_name;
    private String tag;

    public RecommendedManagerDTO(Long id, String first_name, String last_name, String tag) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.tag = tag;
    }


}
