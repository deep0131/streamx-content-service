package com.streamx.contentservice.service;

import com.streamx.contentservice.dto.MovieRequest;
import com.streamx.contentservice.dto.MovieResponse;
import com.streamx.contentservice.model.Genre;
import com.streamx.contentservice.model.Movie;
import com.streamx.contentservice.model.VideoStatus;
import com.streamx.contentservice.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentService {

    private final ContentRepository contentRepository;

    public MovieResponse addMovie(MovieRequest request) {

        log.info("Adding movie: {}", request.getTitle());

        Movie movie = Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .genre(request.getGenre())
                .releaseYear(request.getReleaseYear())
                .director(request.getDirector())
                .cast(request.getCast())
                .rating(request.getRating())
                .thumbnailUrl(request.getThumbnailUrl())
                .durationMinutes(request.getDurationMinutes())
                .videoStatus(VideoStatus.PENDING)
                .build();

        Movie savedMovie = contentRepository.save(movie);

        return mapToResponse(savedMovie);
    }

    public List<MovieResponse> getAllMovies() {

        return contentRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public MovieResponse getMovieById(String id) {

        Movie movie = contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        return mapToResponse(movie);
    }

    public List<MovieResponse> getMoviesByGenre(Genre genre) {

        return contentRepository.findByGenre(genre).stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<MovieResponse> searchMovies(String title) {

        return contentRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::mapToResponse)
                .toList();
    }

    private MovieResponse mapToResponse(Movie savedMovie) {
        return MovieResponse.builder()
                .id(savedMovie.getId())
                .title(savedMovie.getTitle())
                .description(savedMovie.getDescription())
                .genre(savedMovie.getGenre())
                .releaseYear(savedMovie.getReleaseYear())
                .director(savedMovie.getDirector())
                .cast(savedMovie.getCast())
                .rating(savedMovie.getRating())
                .thumbnailUrl(savedMovie.getThumbnailUrl())
                .durationMinutes(savedMovie.getDurationMinutes())
                .videoKey(savedMovie.getVideoKey())
                .hlsUrl(savedMovie.getHlsUrl())
                .videoStatus(savedMovie.getVideoStatus())
                .build();
    }
}
