package com.streamx.contentservice.repository;

import com.streamx.contentservice.model.Genre;
import com.streamx.contentservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ContentRepository extends JpaRepository<Movie, String> {

    List<Movie> findByGenre(Genre genre);

    List<Movie> findByTitleContainingIgnoreCase(String title);
}
