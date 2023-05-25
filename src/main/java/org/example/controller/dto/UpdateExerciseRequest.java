package org.example.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class UpdateExerciseRequest {

    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    private String imageUrl;

}
