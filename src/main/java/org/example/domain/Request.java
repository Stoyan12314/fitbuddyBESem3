package org.example.domain;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
