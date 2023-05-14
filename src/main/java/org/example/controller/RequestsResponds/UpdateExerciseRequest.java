package org.example.controller.RequestsResponds;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateExerciseRequest {

    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @NotBlank
    private String imageUrl;

}
