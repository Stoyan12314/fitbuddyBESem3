package org.example.persistence.entity;

import lombok.*;
import java.time.LocalDate;
import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "exercise_id")
    private Long exerciseId;


    @Column(name = "reps")
    private Integer reps;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "date")
    private LocalDate date;

}
