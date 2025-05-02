package com.malinda.movie_rating.services;

import com.malinda.movie_rating.dtos.resquest.RatingSaveDTO;

public interface RatingService {
    String addRating(RatingSaveDTO dto);
}
