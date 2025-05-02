package com.malinda.movie_rating.service.IMPL;

import com.malinda.movie_rating.dtos.resquest.MovieSaveDTO;
import com.malinda.movie_rating.entities.Genre;
import com.malinda.movie_rating.entities.Movie;
import com.malinda.movie_rating.repositories.MovieRepository;
import com.malinda.movie_rating.services.IMPL.MovieServiceIMPL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceIMPLTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MovieServiceIMPL movieServiceIMPL;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Inception");
        movie.setGenre(Genre.ACTION);
        movie.setReleaseDate(LocalDate.of(2010, 7, 16));
    }

    @Test
    void movieSave_Success() {
        MovieSaveDTO dto = new MovieSaveDTO("Inception", Genre.ACTION, LocalDate.of(2010, 7, 16));

        when(movieRepository.findByTitleIgnoreCase("Inception")).thenReturn(Optional.empty());
        when(modelMapper.map(dto, Movie.class)).thenReturn(movie);
        when(movieRepository.save(movie)).thenReturn(movie);

        String result = movieServiceIMPL.movieSave(dto);

        assertEquals("Movie saved successfully with title: Inception", result);
    }
}
