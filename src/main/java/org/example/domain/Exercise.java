package org.example.domain;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Exercise {

    private Long id;

    private String name;

    private String description;

    private String picture;



}
