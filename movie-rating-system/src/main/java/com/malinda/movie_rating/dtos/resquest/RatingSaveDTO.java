package com.malinda.movie_rating.dtos.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingSaveDTO {
    private Long movieId;
    private int score;
    private String review;
}
