package com.tfworkers.PDSISystem.Model.DTO;


import lombok.Data;

/**
 * The type Recommended manager dto.
 */
@Data
public class RecommendedManagerDTO {

    private Long id;
    private String first_name;
    private String last_name;
    private String tag;

    /**
     * Instantiates a new Recommended manager dto.
     *
     * @param id         the id
     * @param first_name the first name
     * @param last_name  the last name
     * @param tag        the tag
     */
    public RecommendedManagerDTO(Long id, String first_name, String last_name, String tag) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.tag = tag;
    }


}
