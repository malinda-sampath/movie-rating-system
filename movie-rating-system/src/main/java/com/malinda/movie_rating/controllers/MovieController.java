package com.malinda.movie_rating.controllers;

import com.malinda.movie_rating.dtos.resquest.MovieSaveDTO;
import com.malinda.movie_rating.entities.Genre;
import com.malinda.movie_rating.entities.Movie;
import com.malinda.movie_rating.services.MovieService;
import com.malinda.movie_rating.utils.ResponseBuilder;
import com.malinda.movie_rating.utils.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<StandardResponse<String>> addMovie(@RequestBody MovieSaveDTO movieSaveDTO) {
        String message = movieService.movieSave(movieSaveDTO);
        return ResponseBuilder.success(message, null);
    }

    @GetMapping("/getAll")
    public ResponseEntity<StandardResponse<Page<Movie>>> getAllMovies(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Movie> movies = movieService.getAllMovies(page, size);
        return ResponseBuilder.success("Movies Retrieved Successfully", movies);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<StandardResponse<Movie>> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseBuilder.success("Movie Retrieved Successfully", movie);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StandardResponse<String>> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        String message = movieService.updateMovieById(id, updatedMovie);
        return ResponseBuilder.success(message, null);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<StandardResponse<String>> deleteMovie(@PathVariable Long id) {
        String message = movieService.deleteMovieById(id);
        return ResponseBuilder.success(message, null);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<StandardResponse<Page<Movie>>> getMoviesByGenre(
            @PathVariable Genre genre,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
            ) {
        Page<Movie> movies = movieService.getAllByGenre(genre, page, size);
        return ResponseBuilder.success("Movies Retrieved Successfully", movies);
    }

    @GetMapping("/top-rated")
    public ResponseEntity<StandardResponse<List<Movie>>> getTopRatedMovies() {
        List<Movie> movies = movieService.getTopRatedMovies();
        return ResponseBuilder.success("Top Rated Movies Retrieved Successfully", movies);
    }
}
