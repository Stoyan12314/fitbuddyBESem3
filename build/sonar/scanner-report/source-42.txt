package org.example.controller.dto;

import lombok.*;



import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateExerciseRequest {


    @NotNull
    private String name;

    @NotNull
    private String description;



}
