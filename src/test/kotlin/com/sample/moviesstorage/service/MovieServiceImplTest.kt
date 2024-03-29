package com.sample.moviesstorage.service

import com.sample.moviesstorage.entities.MovieEntity
import com.sample.moviesstorage.repository.MovieEntityRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month
import java.util.*

internal class MovieServiceImplTest {
    private val dataRepository: MovieEntityRepository = mockk(relaxed = true)
    private val movieServiceImpl = MovieServiceImpl(dataRepository)


    @Test
    fun `should call its data source to retrieve movies when no date is passed`() {
        // where
        movieServiceImpl.retrieveAllMovies()
        // then
        verify(exactly = 1) { dataRepository.findAll() }
    }

    @Test
    fun `should call its data source to retrieve movies when date is passed`() {
        // where
        movieServiceImpl.retrieveAllMovies(startDate = LocalDate.of(2024, Month.MAY, 20))
        // then
        verify(exactly = 1) { dataRepository.findAll(any()) }
    }

    @Test
    fun `should call its data source to retrieve movies and filter screen`() {
        every { dataRepository.findAll() } returns listOf(
            MovieEntity(
                name = "Captain America: Summer Soldier",
                screen = "Standard"
            ),
            MovieEntity(
                name = "Captain America: Summer Soldier",
                screen = "IMAX"
            )
        )
        // where
        val results = movieServiceImpl.retrieveAllMovies(screenType = "IMAX")

        // then
        verify(exactly = 1) { dataRepository.findAll() }
        assertThat(results).hasSize(1)
        assertThat(results[0].screen).isEqualTo("IMAX")
    }

    @Test
    fun `should call its data source to retrieve movie by id when present`() {
        val id = 4L
        every { dataRepository.findById(4) } returns
                Optional.of(
                    MovieEntity(
                        id = id,
                        name = "Captain America: Summer Soldier",
                        screen = "Standard"
                    )
                )
        // where
        val result = movieServiceImpl.retriveMovie(id)

        // then
        verify(exactly = 1) { dataRepository.findById(id) }
        assertThat(result).isNotEmpty
        assertThat(result.get().name).isEqualTo("Captain America: Summer Soldier")
    }
}
