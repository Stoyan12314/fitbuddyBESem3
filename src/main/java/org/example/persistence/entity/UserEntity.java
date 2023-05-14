package org.example.persistence.entity;

import lombok.*;
import org.example.domain.Role;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 2 ,max = 50)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Column(name = "role", columnDefinition = "ENUM('CUSTOMER', 'TRAINER', 'ADMINISTRATION')")
    @Enumerated(EnumType.STRING)
    private Role role;
}
