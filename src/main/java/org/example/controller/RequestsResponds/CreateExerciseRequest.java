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
    @Min(1)
    @Max(100000)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;
    @NotNull
    private String picture;




}
