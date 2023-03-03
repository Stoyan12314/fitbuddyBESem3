package org.example.controller.RequestsResponds;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class CreateExerciseResponse {
    private String status;
}
