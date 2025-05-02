package com.malinda.movie_rating.services;

import com.malinda.movie_rating.dtos.resquest.RatingSaveDTO;

import java.util.List;

public interface RatingService {
    String addRating(RatingSaveDTO dto);

    List<String> getReviewsByTitleAndRating(String title, int rating);
}
