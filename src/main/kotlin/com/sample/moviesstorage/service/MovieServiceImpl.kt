package com.sample.moviesstorage.service

import com.sample.moviesstorage.dto.Movie
import com.sample.moviesstorage.dto.MovieSlot
import com.sample.moviesstorage.entities.MovieEntity
import com.sample.moviesstorage.repository.MovieEntityRepository
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service
import java.util.*


@Service
class MovieServiceImpl(private val movieEntityRepository: MovieEntityRepository) : MovieService {
    override fun retrieveAllMovies(startDate: Date?, screenType: String?): List<Movie> {
        val moviesList = movieEntityRepository.findAll().map { movie ->
            Movie(
                movie.name, slot = movie.slots
                    .map { slot -> MovieSlot(slot.date, slot.time) }.toList(), screen = movie.screen
            )
        }.toList()
        return moviesList
    }

    override fun retriveMovie(movieId: Long): Optional<Movie> {
        return movieEntityRepository.findById(movieId).map { movie ->
            Movie(
                movie.name, slot = movie.slots
                    .map { slot -> MovieSlot(slot.date, slot.time) }.toList(), screen = movie.screen
            )
        }
    }

    override fun updateMovie(movieId: Long, updatedMovie: Movie) {
        var movieEntity = movieEntityRepository.findById(movieId)
            .orElseThrow { RuntimeException("No such user exists") }
        BeanUtils.copyProperties(updatedMovie, movieEntity)
        movieEntityRepository.save(movieEntity)
    }
}