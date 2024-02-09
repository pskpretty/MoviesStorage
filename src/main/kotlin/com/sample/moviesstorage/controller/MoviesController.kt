package com.sample.moviesstorage.controller

import com.sample.moviesstorage.dto.Movie
import com.sample.moviesstorage.service.MovieService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class MoviesController(private val movieService: MovieService) {
    @GetMapping("/movies")
    fun retrieveMovies(
        @RequestParam("startDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") startDate: Date,
        @RequestParam("screenType", required = false) screenType: String
    ): List<Movie> {
        return movieService.retrieveAllMovies(startDate, screenType)
    }

    @GetMapping("/movies/{id}")
    fun retrieveMovie(@PathVariable id: Long): Optional<Movie> {
        return movieService.retriveMovie(id)
    }

    @PutMapping("/movies/{id}")
    fun updateMovie(@PathVariable id: Long, @RequestBody movie: Movie) {
        movieService.updateMovie(id, movie)
    }
}