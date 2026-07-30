package com.streamx.contentservice.controller;

import com.streamx.contentservice.dto.MovieRequest;
import com.streamx.contentservice.dto.MovieResponse;
import com.streamx.contentservice.model.Genre;
import com.streamx.contentservice.service.ContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
@Slf4j
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<MovieResponse> addMovie(
            @Valid @RequestBody MovieRequest movieRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contentService.addMovie(movieRequest));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies(){

        return ResponseEntity.ok(contentService.getAllMovies());
    }

    @GetMapping("/id/{movieId}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable String movieId){

        return ResponseEntity.ok(contentService.getMovieById(movieId));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieResponse>> getMoviesByGenre(@PathVariable Genre Genre){

        return ResponseEntity.ok(contentService.getMoviesByGenre(Genre));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> searchMovies(@RequestParam String title){

        return ResponseEntity.ok(contentService.searchMovies(title));
    }
}
