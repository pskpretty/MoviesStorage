package com.sample.moviesstorage.configuration

import com.sample.moviesstorage.entities.MovieEntity
import com.sample.moviesstorage.entities.MovieSlotEntity
import com.sample.moviesstorage.repository.MovieEntityRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.Month

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(
        movieEntityRepository: MovieEntityRepository,
    ) = ApplicationRunner {


        movieEntityRepository.save(
            MovieEntity(
                name = "Captain America: Summer Soldier",
                screen = "Standard",
                slots = listOf(
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 20), time = "1345"),
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 20), time = "1600"),
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 1), time = "2000")
                )
            )
        )
        movieEntityRepository.save(
            MovieEntity(
                name = "Captain America: Summer Soldier", slots = listOf(
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 20), time = "1300")
                ), screen = "IMAX"
            )
        )
        movieEntityRepository.save(
            MovieEntity(

                name = "Mission Impossible: Infinite Possibilities",
                screen = "Standard",
                slots = listOf(
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 20), time = "1000"),
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 20), time = "1300"),
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 21), time = "1000"),
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 21), time = "1300"),
                )
            )
        )
        movieEntityRepository.save(
            MovieEntity(
                name = "Batman Kapow! Bam! Sok! Blap!",
                screen = "Standard",
                slots = listOf(
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 20), time = "1000"),
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 21), time = "1300")
                )
            )
        )
        movieEntityRepository.save(
            MovieEntity(
                name = "Batman Kapow! Bam! Sok! Blap!",
                screen = "IMAX",
                slots = listOf(
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 20), time = "1300"),
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 21), time = "1000"),
                    MovieSlotEntity(date = LocalDate.of(2024, Month.MAY, 22), time = "1400")
                )
            )
        )

    }
}