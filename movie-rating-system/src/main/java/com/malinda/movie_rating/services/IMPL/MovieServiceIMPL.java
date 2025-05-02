package com.malinda.movie_rating.services.IMPL;

import com.malinda.movie_rating.dtos.resquest.MovieSaveDTO;
import com.malinda.movie_rating.entities.Genre;
import com.malinda.movie_rating.entities.Movie;
import com.malinda.movie_rating.entities.Rating;
import com.malinda.movie_rating.exceptions.AlreadyExistsException;
import com.malinda.movie_rating.exceptions.ResourceNotFoundException;
import com.malinda.movie_rating.repositories.MovieRepository;
import com.malinda.movie_rating.services.MovieService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceIMPL implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public String movieSave(MovieSaveDTO movieSaveDTO) {

        // Validate title
        if (movieSaveDTO.getTitle() == null || movieSaveDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Movie title cannot be null or empty");
        }

        // Check if the movie already exists
        if (movieRepository.findByTitleIgnoreCase(movieSaveDTO.getTitle()).isPresent()) {
            throw new AlreadyExistsException("Movie with this title already exists: " + movieSaveDTO.getTitle());
        }

        // Map DTO to entity
        Movie movie = modelMapper.map(movieSaveDTO, Movie.class);

        // Save to DB
        try {
            movieRepository.save(movie);
        } catch (Exception e) {
            throw new RuntimeException("Error saving movie: " + e.getMessage());
        }
        return "Movie saved successfully with title: " + movie.getTitle();
    }

    @Override
    public Page<Movie> getAllMovies(int page, int size) {
        return movieRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    @Override
    public String updateMovieById(Long id, Movie updatedMovie) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + id));

        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
        movieRepository.save(existingMovie);

        return "Movie updated successfully with ID: " + id;
    }

    @Override
    public Page<Movie> getAllByGenre(Genre genre, int page, int size) {
        Page<Movie> movies = movieRepository.findAllByGenre(genre, PageRequest.of(page, size));
        if (movies.isEmpty()) {
            throw new RuntimeException("No movies found for genre: " + genre);
        }
        return movies;
    }

    @Override
    public String deleteMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + id));

        movieRepository.delete(movie);
        return "Movie deleted successfully with ID: " + id;
    }

    @Override
    public List<Movie> getTopRatedMovies() {
        List<Movie> allMovies = movieRepository.findAll();

        return allMovies.stream()
                .filter(movie -> movie.getRatings().size() >= 5)
                .sorted((m1, m2) -> {
                    double avg1 = calculateAverageRating(m1);
                    double avg2 = calculateAverageRating(m2);
                    return Double.compare(avg2, avg1); // descending
                })
                .collect(Collectors.toList());
    }

    private double calculateAverageRating(Movie movie) {
        return movie.getRatings().stream()
                .mapToInt(Rating::getScore)
                .average()
                .orElse(0.0);
    }



}
