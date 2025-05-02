package com.malinda.movie_rating.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponse<T> {
    private int status;
    private String message;
    private T data;
}
