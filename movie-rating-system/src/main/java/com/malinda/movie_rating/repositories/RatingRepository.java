package com.malinda.movie_rating.repositories;

import com.malinda.movie_rating.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RatingRepository extends JpaRepository<Rating, Long> {
    // Custom query methods can be defined here if needed
}
