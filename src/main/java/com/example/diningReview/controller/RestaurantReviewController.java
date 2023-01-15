package com.example.diningReview.controller;

import com.example.diningReview.model.AdminReviewStatus;
import com.example.diningReview.model.RestaurantReview;
import com.example.diningReview.repository.RestaurantRepository;
import com.example.diningReview.repository.RestaurantReviewRepository;
import com.example.diningReview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class RestaurantReviewController {
    @Autowired
    private RestaurantReviewRepository restaurantReviewRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/dining-review")
    public RestaurantReview createNewReview(@RequestBody RestaurantReview restaurantReview){
      if(restaurantRepository.findById(restaurantReview.getRestaurantId()).isEmpty()){
          System.out.println("The restaurant is not exist!");
      }
      if(userRepository.findByUserName(restaurantReview.getDisplayName()).isEmpty()){
          System.out.println("The user is not exist!");
      }
      return restaurantReviewRepository.save(restaurantReview);
    }
    @GetMapping("/dining-review")
    public Iterable<RestaurantReview> getAllReviews(){
        return restaurantReviewRepository.findAll();
    }

    @GetMapping("/dining-review/pending")
    public Iterable<RestaurantReview> getPendingReviews(){
        return restaurantReviewRepository.findByAdminReviewStatus(AdminReviewStatus.PENDING);
    }
    @GetMapping("/dining-review/accepted/{id}")
    public Iterable<RestaurantReview> getAcceptedReviews(@PathVariable(name = "id") Long id){
        return restaurantReviewRepository.findByAdminReviewStatusWithId(id, AdminReviewStatus.ACCEPTED);
    }
    @PutMapping("/dining-review/{id}/approve")
    public RestaurantReview approveReview(@PathVariable(name= "id") Long id){
        Optional<RestaurantReview> reviewOptional = restaurantReviewRepository.findById(id);
        if(reviewOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review does not exist");
        }
        RestaurantReview reviewAccepted = reviewOptional.get();
        reviewAccepted.setAdminReviewStatus(AdminReviewStatus.ACCEPTED);
        return restaurantReviewRepository.save(reviewAccepted);
    }
    @PutMapping("/dining-review/{id}/reject")
    public RestaurantReview rejectReview(@PathVariable(name= "id") Long id){
        Optional<RestaurantReview> reviewOptional = restaurantReviewRepository.findById(id);
        if(reviewOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review does not exist");
        }
        RestaurantReview reviewRejected = reviewOptional.get();
        reviewRejected.setAdminReviewStatus(AdminReviewStatus.REJECTED);
        return restaurantReviewRepository.save(reviewRejected);
    }
    @PutMapping("/dining-review/{id}/pending")
    public RestaurantReview pendingReview(@PathVariable(name= "id") Long id){
        Optional<RestaurantReview> reviewOptional = restaurantReviewRepository.findById(id);
        if(reviewOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review does not exist");
        }
        RestaurantReview reviewPending = reviewOptional.get();
        reviewPending.setAdminReviewStatus(AdminReviewStatus.PENDING);
        return restaurantReviewRepository.save(reviewPending);
    }

}
