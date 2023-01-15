package com.example.diningReview.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RESTAURANT_REVIEW")
@NoArgsConstructor
@Getter
@Setter
public class RestaurantReview {
    @Column(name="DISPLAY-NAME")
    private String displayName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;
    @Column(name = "PEANUT")
    private Integer peanutScore;
    @Column(name = "EGG")
    private Integer eggScore;
    @Column(name = "DAIRY")
    private Integer dairyScore;
    @Column(name = "COMMENTARY")
    private String commentary;
    @Column(name = "REVIEW-STATUS")
    @Enumerated(EnumType.STRING)
    private AdminReviewStatus adminReviewStatus;
}
