package com.sample.moviesstorage.service

import com.sample.moviesstorage.entities.MovieEntity
import java.time.LocalDate
import java.util.*

interface MovieService {
    fun retrieveAllMovies(startDate: LocalDate? = null, screenType: String? = null):List<MovieEntity>

    fun retriveMovie(movieId:Long):Optional<MovieEntity>

    fun updateMovie(movieId: Long,updatedMovie: MovieEntity)
}