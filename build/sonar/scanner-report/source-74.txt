package org.example.domain;

import lombok.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Request {

    private Long id;

    private LocalDate date;
    private Long userId;
    private Long exerciseId;

    private Integer reps;

    private Double weight;



}
