package com.example.diningReview.controller;

import com.example.diningReview.model.Restaurant;
import com.example.diningReview.model.RestaurantReview;
import com.example.diningReview.repository.RestaurantRepository;
import com.example.diningReview.repository.RestaurantReviewRepository;
import com.example.diningReview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/restaurants")
    public Iterable<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }
    @GetMapping("/restaurants/{id}")
    public Optional<Restaurant> getRestaurantBy(@PathVariable(name = "id") Long id){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if(optionalRestaurant.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This restaurant does not exist");
        }
        return restaurantRepository.findById(id);
    }
    @GetMapping("/restaurants/search")
    public Optional<Restaurant> getRestaurantByZipcode(@RequestParam String zipcode){
        return restaurantRepository.findByZipcode(zipcode);
    }
    @PostMapping("/restaurants")
    public Restaurant createNewRestaurant(@RequestBody Restaurant restaurant){
        if(restaurantRepository.existByPostcode(restaurant.getPostcode()) && restaurantRepository.existByName(restaurant.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A restaurant with this name and postcode already exists");
        }
        return restaurantRepository.save(restaurant);
    }
}
