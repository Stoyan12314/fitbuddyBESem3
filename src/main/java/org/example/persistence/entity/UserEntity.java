package org.example.persistence.entity;
import lombok.*;
import org.example.domain.Role;

@Data
@Getter
@Setter
@Builder
public class UserEntity {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

}
