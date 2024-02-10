package com.sample.moviesstorage.repository

import com.sample.moviesstorage.entities.MovieEntity
import com.sample.moviesstorage.entities.MovieSlotEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDate
import java.time.Month

@DataJpaTest
class MovieEntityRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val movieEntityRepository: MovieEntityRepository
) {

    @Test
    fun `when findById then return movie`() {
        val movie1 = MovieEntity(
            name = "Captain America: Summer Soldier",
            screen = "Standard",
            slots = listOf(
                MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 20), time = "1345"),
                MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 20), time = "1600"),
                MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 1), time = "2000")
            )
        )
        entityManager.persist(movie1)
        entityManager.flush()
        val found = movieEntityRepository.findById(movie1.id!!)
        assertTrue(found.isPresent)
        assertThat(found.get()).isEqualTo(movie1)
    }

    @Test
    fun `when findById for invalid id then return empty`() {
        val found = movieEntityRepository.findById(5)
        assertTrue(found.isEmpty)
    }

    @AfterEach
    fun cleanUp() {
        movieEntityRepository.deleteAll()
    }

}