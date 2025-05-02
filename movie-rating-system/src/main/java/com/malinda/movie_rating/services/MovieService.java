package com.malinda.movie_rating.services;

import com.malinda.movie_rating.dtos.resquest.MovieSaveDTO;
import com.malinda.movie_rating.entities.Genre;
import com.malinda.movie_rating.entities.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {
    String movieSave(MovieSaveDTO movieSaveDTO);

    List<Movie> getAllMovies();

    Movie getMovieById(Long id);

    String updateMovieById(Long id,Movie movie);

    List<Movie> getAllByGenre(Genre genre);

    String deleteMovieById(Long id);

    List<Movie> getTopRatedMovies();
}
