package org.example.persistence.entity;

import lombok.*;

@Data
@Getter
@Setter
@Builder
public class ExerciseEntity {
    private Long id;

    private String name;

    private String description;

    private String picture;




}
