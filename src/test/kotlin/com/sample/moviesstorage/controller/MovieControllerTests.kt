package com.sample.moviesstorage.controller

import com.ninjasquad.springmockk.MockkBean
import com.sample.moviesstorage.entities.MovieEntity
import com.sample.moviesstorage.service.MovieService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class MovieControllerTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var movieService: MovieService

    @Test
    fun `retrieve movies`() {
        val movie1 = MovieEntity(
            name = "Captain America: Summer Soldier",
            screen = "Standard"
        )
        every { movieService.retrieveAllMovies() } returns listOf(movie1)
        mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].name").value(movie1.name))
            .andExpect(jsonPath("\$.[0].screen").value(movie1.screen))
    }

    @Test
    fun `retrieve movies should successfully return empty when db is empty`() {
        every { movieService.retrieveAllMovies() } returns emptyList()
        mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isEmpty)

    }
}