package com.malinda.movie_rating.advisor;

import com.malinda.movie_rating.exceptions.AlreadyExistsException;
import com.malinda.movie_rating.exceptions.MovieNotFoundException;
import com.malinda.movie_rating.exceptions.ResourceNotFoundException;
import com.malinda.movie_rating.utils.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardResponse<String>> handleResourceNotFound(ResourceNotFoundException ex) {
        logger.error("Resource not found: {}", ex.getMessage());
        return new ResponseEntity<>(
                new StandardResponse<>(404, "Resource Not Found: " + ex.getMessage(), null),
                HttpStatus.NOT_FOUND
        );
    }

    // Handle AlreadyExistsException
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<StandardResponse<String>> handleAlreadyExistsException(AlreadyExistsException ex) {
        logger.warn("Conflict: {}", ex.getMessage());
        return new ResponseEntity<>(
                new StandardResponse<>(409, "Conflict: " + ex.getMessage(), null),
                HttpStatus.CONFLICT
        );
    }

    // Global fallback for any uncaught exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse<String>> handleGenericException(Exception ex) {
        logger.error("Internal Server Error: ", ex);
        return new ResponseEntity<>(
                new StandardResponse<>(500, "An unexpected error occurred.", null),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // Handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardResponse<String>> handleIllegalArgument(IllegalArgumentException ex) {
        logger.warn("Validation failed: {}", ex.getMessage());
        return new ResponseEntity<>(
                new StandardResponse<>(400, "Bad Request: " + ex.getMessage(), null),
                HttpStatus.BAD_REQUEST
        );
    }
}

