package com.example.diningReview.repository;

import com.example.diningReview.model.AdminReviewStatus;
import com.example.diningReview.model.RestaurantReview;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantReviewRepository extends CrudRepository<RestaurantReview, Long> {
    Iterable<RestaurantReview> findByAdminReviewStatus(AdminReviewStatus adminReviewStatus);
    Iterable<RestaurantReview> findByAdminReviewStatusWithId(Long id, AdminReviewStatus adminReviewStatus);

    Iterable<RestaurantReview> findUserReviews(RestaurantReview restaurantReview);

}
