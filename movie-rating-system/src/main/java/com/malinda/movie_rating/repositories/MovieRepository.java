package com.malinda.movie_rating.repositories;

import com.malinda.movie_rating.entities.Genre;
import com.malinda.movie_rating.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Object> findByTitleIgnoreCase(String title);

    List<Movie> findAllByGenre(Genre genre);
    // Custom query methods can be defined here if needed
}
