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
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Length(min = 2 ,max = 50)
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;


    @Column(name = "role", columnDefinition = "ENUM('CUSTOMER', 'TRAINER', 'ADMINISTRATION')")
    @Enumerated(EnumType.STRING)
    private Role role;

}
