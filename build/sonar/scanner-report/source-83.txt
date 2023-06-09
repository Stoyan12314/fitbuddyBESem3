package org.example.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="exercise_id", nullable=false)
    private ExerciseEntity exercise;

    @Column(name = "date")
    private LocalDate date;
}
