package org.example.controller.RequestsResponds;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
