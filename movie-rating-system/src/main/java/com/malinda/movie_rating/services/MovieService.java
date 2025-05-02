package com.malinda.movie_rating.services;

import com.malinda.movie_rating.dtos.resquest.MovieSaveDTO;
import com.malinda.movie_rating.entities.Genre;
import com.malinda.movie_rating.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {
    String movieSave(MovieSaveDTO movieSaveDTO);

    Page<Movie> getAllMovies(int page, int size);

    Movie getMovieById(Long id);

    String updateMovieById(Long id,Movie movie);

    Page<Movie> getAllByGenre(Genre genre, int page, int size);

    String deleteMovieById(Long id);

    List<Movie> getTopRatedMovies();
}
