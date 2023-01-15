package com.example.diningReview.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="RESTAURANT")
@Setter @Getter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "POSTCODE")
    private String postcode;
    @Column(name = "RESTAURANT_NAME")
    private String name;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "PEANUT")
    private Integer peanutScore;
    @Column(name = "EGG")
    private Integer eggScore;
    @Column(name = "DAIRY")
    private Integer dairyScore;


}
