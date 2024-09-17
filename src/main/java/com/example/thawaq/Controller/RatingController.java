package com.example.thawaq.Controller;

import com.example.thawaq.Api.ApiResponse;
import com.example.thawaq.Model.Rating;
import com.example.thawaq.Model.User;
import com.example.thawaq.Service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    @GetMapping("/get-all")
    public ResponseEntity getAllRatings() {
        return ResponseEntity.status(200).body(ratingService.getAllRatings());
    }


    //Jana
    @GetMapping("/get-my-client")
    public ResponseEntity getMyRatingForClient(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(ratingService.getMyRatingForClient(user.getId()));}

    //Jana
    @GetMapping("/get-my-expert")
    public ResponseEntity getMyRatingForExpert(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(ratingService.getMyRatingForExpert(user.getId()));
    }

    //Jana
    @PostMapping("/user/add-rating/{storeId}")
    public ResponseEntity addRatingToStore(@Valid @RequestBody Rating rating,@AuthenticationPrincipal User user,@PathVariable Integer storeId) {
        ratingService.addRatingFromUserToStore(rating,user.getId(),storeId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added User rating to store!"));
    }
    //Jana
    @PostMapping("/expert/add-rating/{storeId}")
    public ResponseEntity addRatingFromExpertToStore(@Valid @RequestBody Rating rating,@AuthenticationPrincipal User user,@PathVariable Integer storeId) {
        ratingService.addRatingFromExpertToStore(rating,user.getId(),storeId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added Expert rating to store!"));
    }
    //Jana
    @PutMapping("/update-rating/{ratingId}")
    public ResponseEntity updateRatingToStore(@Valid @RequestBody Rating rating, @AuthenticationPrincipal User user, @PathVariable Integer ratingId) {
        ratingService.updateRating(rating,user.getId(),ratingId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated rating!"));}

    //Jana
    @DeleteMapping("/delete-rating/{ratingId}")
    public ResponseEntity deleteRating(@AuthenticationPrincipal User user, @PathVariable Integer ratingId) {
        ratingService.deleteRating(user.getId(),ratingId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted rating!"));
    }

    ///v2
    @GetMapping("/average-rating-expert/{expertId}")
    public ResponseEntity getAverageRatingExpert(@PathVariable Integer expertId) {
        return ResponseEntity.status(200).body(ratingService.CalculateAverageRatingExpert(expertId));
    }
    ///v2
    @GetMapping("/average-rating-store/{storeId}")
    public ResponseEntity getAverageRatingStore(@PathVariable Integer storeId) {
        ratingService.CalculateAverageStore(storeId);
        return ResponseEntity.status(200).body(ratingService.CalculateAverageStore(storeId));
    }

    @GetMapping("/get-both-by-cleaning-of-rating")  //v2
    public ResponseEntity getBOTHByCleaningOfRating()
    {
        return ResponseEntity.status(200).body(ratingService.getBOTHByCleaningOfRating());
    }
    @GetMapping("/get-restaurant-by-cleaning-of-rating")  //v2
    public ResponseEntity getRestaurantByCleaningOfRating()
    {
        return ResponseEntity.status(200).body(ratingService.getRestaurantByCleaningOfRating());
    }
    @GetMapping("/get-cafe-by-cleaning-of-rating")  //v2
    public ResponseEntity getCafeByCleaningOfRating()
    {
        return ResponseEntity.status(200).body(ratingService.getCafeByCleaningOfRating());
    }
    @GetMapping("/get-branch-both-by-cleaning-of-rating")  //v2
    public ResponseEntity getBranchBOTHByCleaningOfRating()
    {
        return ResponseEntity.status(200).body(ratingService.getBranchBOTHByCleaningOfRating());
    }
    @GetMapping("/get-branch-restaurant-by-cleaning-of-rating")  //v2
    public ResponseEntity getBranchRestaurantByCleaningOfRating()
    {
        return ResponseEntity.status(200).body(ratingService.getBranchRestaurantByCleaningOfRating());
    }
    @GetMapping("/get-branch-cafe-by-cleaning-of-rating")  //v2
    public ResponseEntity getBranchCafeByCleaningOfRating()
    {
        return ResponseEntity.status(200).body(ratingService.getBranchCafeByCleaningOfRating());
    }
    @GetMapping("/get-menu-both-by-cleaning-of-rating")  //v2
    public ResponseEntity getMenuBOTHByCleaningOfRating()
    {
        return ResponseEntity.status(200).body(ratingService.getMenuBOTHByCleaningOfRating());
    }
    @GetMapping("/get-menu-restaurant-by-cleaning-of-rating")  //v2
    public ResponseEntity getMenuRestaurantByCleaningOfRating()
    {
        return ResponseEntity.status(200).body(ratingService.getMenuRestaurantByCleaningOfRating());
    }
    @GetMapping("/get-menu-cafe-by-cleaning-of-rating")  //v2
    public ResponseEntity getMenuCafeByCleaningOfRating()
    {
        return ResponseEntity.status(200).body(ratingService.getMenuCafeByCleaningOfRating());
    }
    @GetMapping("/get-rating-by-latest")  //v2
    public ResponseEntity getRatingByLatest()
    {
        return ResponseEntity.status(200).body(ratingService.getRatingByLatest());
    }
    @GetMapping("/get-rating-by-high")  //v2
    public ResponseEntity getRatingByHigh()
    {
        return ResponseEntity.status(200).body(ratingService.getRatingByHigh());
    }
    @GetMapping("/get-rating-by-low")  //v2
    public ResponseEntity getRatingByLow()
    {
        return ResponseEntity.status(200).body(ratingService.getRatingByLow());
    }
    @GetMapping("/get-rating-by-comment/{comment}")  //v2
    public ResponseEntity getRatingByComment(@PathVariable String comment)
    {
        return ResponseEntity.status(200).body(ratingService.getRatingByComment(comment));
    }

    //V3 Jana
    @GetMapping("/average-rating-store-service/{storeId}")
    public ResponseEntity CalculateAverageServiceStore(@PathVariable Integer storeId) {
        ratingService.CalculateAverageServiceStore(storeId);
        return ResponseEntity.status(200).body(new ApiResponse("successfully Calculate average service!"));
    }
    //V3
    @GetMapping("/top-4-cafes")
    public ResponseEntity getTop4CafesByAverageRating(){
        return ResponseEntity.status(200).body(ratingService.getTop4CafesByAverageRating());}
    //V3
    @GetMapping("/top-4-restaurant")
    public ResponseEntity getTop4RestaurantByAverageRating(){
        return ResponseEntity.status(200).body(ratingService.getTop4RestaurantByAverageRating());}




}
