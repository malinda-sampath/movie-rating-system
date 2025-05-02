package com.malinda.movie_rating.services.IMPL;

import com.malinda.movie_rating.dtos.resquest.RatingSaveDTO;
import com.malinda.movie_rating.entities.Movie;
import com.malinda.movie_rating.entities.Rating;
import com.malinda.movie_rating.repositories.MovieRepository;
import com.malinda.movie_rating.repositories.RatingRepository;
import com.malinda.movie_rating.services.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingServiceIMPL implements RatingService {

    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    @Override
    public String addRating(RatingSaveDTO dto) {

        if (dto.getScore() < 1 || dto.getScore() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + dto.getMovieId()));

        Rating rating = new Rating();
        rating.setScore(dto.getScore());
        rating.setReview(dto.getReview());
        rating.setMovie(movie);

        ratingRepository.save(rating);

        return "Rating added successfully!";
    }

    @Override
    public List<String> getReviewsByTitleAndRating(String title, int score) {
        if (score < 1 || score > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        return ratingRepository.findReviewsByMovieTitleAndScore(title, score);
    }

}
