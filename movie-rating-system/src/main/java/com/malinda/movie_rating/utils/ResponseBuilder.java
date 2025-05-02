package com.malinda.movie_rating.utils;

import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

        // For success responses
        public static <T> ResponseEntity<StandardResponse<T>> success(String message, T data) {
            return ResponseEntity.ok(new StandardResponse<>(200, message, data));
        }

        // For error responses
        public static <T> ResponseEntity<StandardResponse<T>> error(String message, T data) {
            return ResponseEntity.status(500).body(new StandardResponse<>(500, message, data));
        }

        // For not found responses (e.g., 404 - Movie Not Found)
        public static <T> ResponseEntity<StandardResponse<T>> notFound(String message) {
            return ResponseEntity.status(404).body(new StandardResponse<>(404, message, null));
        }

        // For bad request responses (e.g., 400 - Invalid Input)
        public static <T> ResponseEntity<StandardResponse<T>> badRequest(String message, T data) {
            return ResponseEntity.status(400).body(new StandardResponse<>(400, message, data));
        }

        // For conflict responses (e.g., 409 - Resource already exists)
        public static <T> ResponseEntity<StandardResponse<T>> conflict(String message, T data) {
            return ResponseEntity.status(409).body(new StandardResponse<>(409, message, data));
        }

        // For unauthorized responses (e.g., 401 - Unauthorized Access)
        public static <T> ResponseEntity<StandardResponse<T>> unauthorized(String message) {
            return ResponseEntity.status(401).body(new StandardResponse<>(401, message, null));
        }

        // For forbidden responses (e.g., 403 - Forbidden Access)
        public static <T> ResponseEntity<StandardResponse<T>> forbidden(String message) {
            return ResponseEntity.status(403).body(new StandardResponse<>(403, message, null));
        }

        // For internal server error responses (e.g., 500 - Unexpected server error)
        public static <T> ResponseEntity<StandardResponse<T>> internalServerError(String message, T data) {
            return ResponseEntity.status(500).body(new StandardResponse<>(500, message, data));
        }

        // For created response (e.g., 201 - Successfully created a resource)
        public static <T> ResponseEntity<StandardResponse<T>> created(String message, T data) {
            return ResponseEntity.status(201).body(new StandardResponse<>(201, message, data));
        }

}
