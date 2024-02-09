package com.sample.moviesstorage.service

import com.sample.moviesstorage.dto.Movie
import com.sample.moviesstorage.dto.MovieSlot
import com.sample.moviesstorage.entities.MovieEntity
import com.sample.moviesstorage.entities.MovieSlotEntity
import com.sample.moviesstorage.repository.MovieEntityRepository
import org.springframework.beans.BeanUtils
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
@CacheConfig(cacheNames = ["moviesCache"])
class MovieServiceImpl(private val movieEntityRepository: MovieEntityRepository) :
    MovieService {
    @Cacheable
    override fun retrieveAllMovies(startDate: LocalDate?, screenType: String?): List<Movie> {
        Thread.sleep(500)
        val moviesList =
            isAfterThanOrEqualTo(startDate)?.let { it ->
                movieEntityRepository.findAll(
                    it
                )
                    .filter { movieEntity -> screenType isNullOr { movieEntity.screen == screenType } }
                    .map { movie ->
                        Movie(
                            movie.name,
                            slot = movie.slots
                                .map { slot -> MovieSlot(slot.date.toString(), slot.time) }.toList(),
                            screen = movie.screen
                        )
                    }.toList()
            }
        return moviesList.orEmpty()
    }

    @Cacheable(key = "#movieId")
    override fun retriveMovie(movieId: Long): Optional<Movie> {
        return movieEntityRepository.findById(movieId).map { movie ->
            Movie(
                movie.name, slot = movie.slots
                    .map { slot -> MovieSlot(slot.date.toString(), slot.time) }.toList(), screen = movie.screen
            )
        }
    }

    @CachePut(key = "#movieId")
    override fun updateMovie(movieId: Long, updatedMovie: Movie) {
        var movieEntity = movieEntityRepository.findById(movieId)
            .orElseThrow { RuntimeException("No such user exists") }
        BeanUtils.copyProperties(updatedMovie, movieEntity)
        movieEntityRepository.save(movieEntity)
    }

    fun isAfterThanOrEqualTo(startDate: LocalDate?): Specification<MovieEntity>? {

        return if (startDate != null) {
            Specification<MovieEntity> { root, cq, cb ->
                val movieEntityEqual = root.join<MovieEntity, MovieSlotEntity>("slots")
                cb.greaterThanOrEqualTo(
                    movieEntityEqual.get("date"), startDate
                )
            }
        } else
            null
    }

    infix fun <T> T?.isNullOr(predicate: (T) -> Boolean): Boolean = if (this != null) predicate(this) else true

}