package com.sample.moviesstorage.service

import com.sample.moviesstorage.entities.MovieEntity
import com.sample.moviesstorage.entities.MovieSlotEntity
import com.sample.moviesstorage.repository.MovieEntityRepository
import org.springframework.cache.annotation.CacheConfig
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
    override fun retrieveAllMovies(startDate: LocalDate?, screenType: String?): List<MovieEntity> {
        Thread.sleep(500)

        val moviesList =
            when (startDate) {
                null -> movieEntityRepository.findAll()
                else ->
                    isAfterThanOrEqualTo(startDate)?.let {
                        movieEntityRepository.findAll(
                            it
                        )
                    }
            }

        return moviesList?.filter { movieEntity -> screenType isNullOr { movieEntity.screen == screenType } }
            ?.toList().orEmpty()
    }

    @Cacheable(key = "#movieId")
    override fun retriveMovie(movieId: Long): Optional<MovieEntity> {
        return movieEntityRepository.findById(movieId)
    }

    override fun updateMovie(movieId: Long, updatedMovie: MovieEntity) {
        movieEntityRepository.findById(movieId)
            .orElseThrow { RuntimeException("No such movie present") }
        movieEntityRepository.save(updatedMovie)
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