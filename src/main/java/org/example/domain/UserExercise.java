package org.example.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserExercise {
    private Long id;
    private User user;
    private Exercise exercise;
}
