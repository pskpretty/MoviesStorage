package com.sample.moviesstorage.repository

import com.sample.moviesstorage.entities.MovieEntity
import org.springframework.data.repository.CrudRepository

interface MovieEntityRepository: CrudRepository<MovieEntity, Long>