package com.sample.moviesstorage.service

import com.sample.moviesstorage.dto.Movie
import java.util.*

interface MovieService {
    fun retrieveAllMovies(startDate: Date? = null, screenType: String? = null):List<Movie>

    fun retriveMovie(movieId:Long):Optional<Movie>

    fun updateMovie(movieId: Long,updatedMovie: Movie)
}