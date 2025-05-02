package com.malinda.movie_rating.repositories;

import com.malinda.movie_rating.entities.Genre;
import com.malinda.movie_rating.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Object> findByTitleIgnoreCase(String title);

    Page<Movie> findAllByGenre(Genre genre, Pageable pageable);
    // Custom query methods can be defined here if needed
}
