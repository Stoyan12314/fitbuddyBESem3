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

    private LocalDateTime date;
    private User user;
    private Exercise exercise;

    private Integer reps;

    private Double weight;



}
