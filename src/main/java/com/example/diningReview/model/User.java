package com.example.diningReview.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "ZIPCODE")
    private  Integer zipcode;
    @Column(name = "POSTCODE")
    private  Integer postcode;
    @Column(name = "HAS-PPEANUT-ALLETGY")
    private Boolean hasPeanutAllergy;
    @Column(name = "HAS-EGG-ALLERGY")
    private Boolean hasEggAllergy;
    @Column(name = "HAS-DAIRY-ALLERGY")
    private Boolean hasDairyAllergy;

}
