package com.malinda.movie_rating.repositories;

import com.malinda.movie_rating.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r.review FROM Rating r WHERE r.movie.title = :title AND r.score = :score AND r.review IS NOT NULL")
    List<String> findReviewsByMovieTitleAndScore(@Param("title") String title, @Param("score") int score);
}
