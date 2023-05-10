package org.example.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;

    private String imageUrl;




}
