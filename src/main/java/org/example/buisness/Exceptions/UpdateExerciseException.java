package org.example.buisness.Exceptions;

import org.springframework.web.server.ResponseStatusException;

public class UpdateExerciseException extends RuntimeException {
    public UpdateExerciseException(String message){
        super(message);
    }
}
