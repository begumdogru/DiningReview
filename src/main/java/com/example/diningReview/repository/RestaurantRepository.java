package com.example.diningReview.repository;

import com.example.diningReview.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;


public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    boolean existByPostcode(String postcode);
    boolean existByName(String name);
    Optional<Restaurant> findByZipcode(String zipcode);


}
