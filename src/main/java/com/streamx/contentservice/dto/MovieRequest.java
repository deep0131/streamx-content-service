package com.streamx.contentservice.dto;

import com.streamx.contentservice.model.Genre;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Genre is required")
    private Genre genre;

    @NotBlank(message = "Release year is required")
    private String releaseYear;

    private String director;

    private String cast;

    private Double rating;

    private String thumbnailUrl;

    private int durationMinutes;
}
