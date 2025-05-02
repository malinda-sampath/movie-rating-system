package com.malinda.movie_rating.controllers;

import com.malinda.movie_rating.dtos.resquest.RatingSaveDTO;
import com.malinda.movie_rating.services.RatingService;
import com.malinda.movie_rating.utils.ResponseBuilder;
import com.malinda.movie_rating.utils.StandardResponse;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
@AllArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/add")
    public ResponseEntity<StandardResponse<String>> addRating(@RequestBody RatingSaveDTO dto) {
        String message = ratingService.addRating(dto);
        return ResponseBuilder.success(message, null);
    }

    @GetMapping("/reviews")
    public ResponseEntity<StandardResponse<List<String>>> getReviewsByTitleAndRating(
            @RequestParam String title,
            @RequestParam int rating
    ) {
        List<String> reviews = ratingService.getReviewsByTitleAndRating(title, rating);
        return ResponseBuilder.success("Reviews Retrieved Successfully", reviews);
    }


}
