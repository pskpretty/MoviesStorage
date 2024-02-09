package com.sample.moviesstorage.controller

import com.sample.moviesstorage.entities.MovieEntity
import com.sample.moviesstorage.service.MovieService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*


@RestController
class MoviesController(private val movieService: MovieService) {
    @GetMapping("/movies")
    fun retrieveMovies(
        @RequestParam("startDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") startDate: LocalDate? = null,
        @RequestParam("screenType", required = false) screenType: String? = null
    ): List<MovieEntity> {
        return movieService.retrieveAllMovies(startDate, screenType)
    }

    @GetMapping("/movies/{id}")
    fun retrieveMovie(@PathVariable id: Long): Optional<MovieEntity> {
        return movieService.retriveMovie(id)
    }

    @PutMapping("/movies/{id}")
    fun updateMovie(@PathVariable id: Long, @RequestBody movie: MovieEntity) {
        movieService.updateMovie(id, movie)
    }
}