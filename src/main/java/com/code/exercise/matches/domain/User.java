package com.code.exercise.matches.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private String jobTitle;
    @Column
    private Integer heightInCm;
    @Column
    private String mainPhoto;
    @Column
    private Float compatibilityScore;
    @Column
    private Integer contactsExchanged;
    @Column
    private Boolean favourite;
    @Column
    private String religion;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "city_name"))
    })
    private City city;

}
