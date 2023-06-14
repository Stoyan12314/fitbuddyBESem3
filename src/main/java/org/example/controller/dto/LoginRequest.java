package org.example.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
