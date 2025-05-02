package com.malinda.movie_rating.dtos.resquest;

import com.malinda.movie_rating.entities.Genre;
import com.malinda.movie_rating.entities.Rating;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MovieSaveDTO {
    private String title;
    private Genre genre;
    private LocalDate releaseDate;
}
