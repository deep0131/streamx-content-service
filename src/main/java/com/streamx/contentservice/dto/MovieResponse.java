package com.streamx.contentservice.dto;

import com.streamx.contentservice.model.Genre;
import com.streamx.contentservice.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {

    private String id;

    private String title;
    private String description;

    private Genre genre;
    private String releaseYear;
    private String director;
    private String cast;
    private Double rating;
    private String thumbnailUrl;

    private int durationMinutes;

    private String videoKey;
    private String hlsUrl;
    private VideoStatus videoStatus;
}
