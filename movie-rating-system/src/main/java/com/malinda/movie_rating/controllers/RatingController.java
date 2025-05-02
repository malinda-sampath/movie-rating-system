package com.malinda.movie_rating.controllers;

import com.malinda.movie_rating.dtos.resquest.RatingSaveDTO;
import com.malinda.movie_rating.services.RatingService;
import com.malinda.movie_rating.utils.ResponseBuilder;
import com.malinda.movie_rating.utils.StandardResponse;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
