package com.sample.moviesstorage.configuration

import com.sample.moviesstorage.dto.Screen
import com.sample.moviesstorage.entities.MovieEntity
import com.sample.moviesstorage.entities.MovieSlotEntity
import com.sample.moviesstorage.repository.MovieEntityRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(
        movieEntityRepository: MovieEntityRepository,
    ) = ApplicationRunner {


        movieEntityRepository.save(
            MovieEntity(
                name = "Captain America: Summer Soldier",
                screen = Screen.Standard,
                slots = listOf(
                    MovieSlotEntity(date = "2024-05-20", time = "1345"),
                    MovieSlotEntity(date = "2024-05-20", time = "1600"),
                    MovieSlotEntity(date = "2024-06-01", time = "2000")
                )
            )
        )
        movieEntityRepository.save(
            MovieEntity(
                name = "Captain America: Summer Soldier", slots = listOf(
                    MovieSlotEntity(date = "2024-05-20", time = "1300")
                ), screen = Screen.IMAX
            )
        )
        movieEntityRepository.save(
            MovieEntity(

                name = "Mission Impossible: Infinite Possibilities",
                screen = Screen.Standard,
                slots = listOf(
                    MovieSlotEntity(date = "2024-05-20", time = "1000"),
                    MovieSlotEntity(date = "2024-05-20", time = "1300"),
                    MovieSlotEntity(date = "2024-05-21", time = "1000"),
                    MovieSlotEntity(date = "2024-05-21", time = "1300"),
                )
            )
        )
        movieEntityRepository.save(
            MovieEntity(
                name = "Batman Kapow! Bam! Sok! Blap!",
                screen = Screen.Standard,
                slots = listOf(
                    MovieSlotEntity(date = "2024-05-20", time = "1000"),
                    MovieSlotEntity(date = "2024-05-21", time = "1300")
                )
            )
        )
        movieEntityRepository.save(
            MovieEntity(
                name = "Batman Kapow! Bam! Sok! Blap!",
                screen = Screen.IMAX,
                slots = listOf(
                    MovieSlotEntity(date = "2024-05-20", time = "1300"),
                    MovieSlotEntity(date = "2024-05-21", time = "1000"),
                    MovieSlotEntity(date = "2024-05-22", time = "1400")
                )
            )
        )

    }
}