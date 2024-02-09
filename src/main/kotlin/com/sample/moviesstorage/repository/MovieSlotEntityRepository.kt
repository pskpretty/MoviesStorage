package com.sample.moviesstorage.repository

import com.sample.moviesstorage.entities.MovieSlotEntity
import org.springframework.data.repository.CrudRepository

interface MovieSlotEntityRepository : CrudRepository<MovieSlotEntity, Long>
