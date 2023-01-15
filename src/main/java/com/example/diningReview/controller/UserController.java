package com.example.diningReview.controller;

import com.example.diningReview.model.Restaurant;
import com.example.diningReview.model.User;
import com.example.diningReview.repository.RestaurantRepository;
import com.example.diningReview.repository.RestaurantReviewRepository;
import com.example.diningReview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private RestaurantReviewRepository restaurantReviewRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
    @PostMapping("/users")
    public User createNewUser(@RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist");
        }
        return userRepository.save(user);

    }

    @GetMapping("/user/{username}")
    public Optional<User> getUserByUsername(@PathVariable(name = "username") String username){
        Optional<User> userOptional = userRepository.findByUserName(username);
        if(userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist");
        }
        return userRepository.findByUserName(username);
    }
    @PutMapping("/users/{userName}")
    public User updateUser(@PathVariable("userName") String userName, @RequestBody User userChanges) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This use does not exist"));

        user.setCity(userChanges.getCity());
        user.setState(userChanges.getState());
        user.setPostcode(userChanges.getPostcode());
        user.setHasPeanutAllergy(userChanges.getHasPeanutAllergy());
        user.setHasEggAllergy(userChanges.getHasEggAllergy());
        user.setHasDairyAllergy(userChanges.getHasEggAllergy());

        return userRepository.save(user);
    }
}
