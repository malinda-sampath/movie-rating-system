package com.malinda.movie_rating.controllers;

import com.malinda.movie_rating.entities.Genre;
import com.malinda.movie_rating.entities.Movie;
import com.malinda.movie_rating.services.MovieService;
import com.malinda.movie_rating.utils.StandardResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private Movie sampleMovie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleMovie = new Movie();
        sampleMovie.setId(1L);
        sampleMovie.setTitle("Interstellar");
        sampleMovie.setGenre(Genre.ACTION);
        sampleMovie.setReleaseDate(LocalDate.of(2014, 11, 7));
    }

    @Test
    void getMovieById_ShouldReturnMovie() {
        // Arrange
        Long movieId = 1L;
        when(movieService.getMovieById(movieId)).thenReturn(sampleMovie);

        // Act
        ResponseEntity<StandardResponse<Movie>> response = movieController.getMovieById(movieId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Movie Retrieved Successfully", response.getBody().getMessage());
        assertEquals("Interstellar", response.getBody().getData().getTitle());
    }
}
