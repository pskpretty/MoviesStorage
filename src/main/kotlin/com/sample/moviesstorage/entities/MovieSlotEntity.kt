package com.sample.moviesstorage.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDate
import java.util.*

@Entity
data class MovieSlotEntity(
    @Id @GeneratedValue var id: Long? = null,
    var date: LocalDate,
    var time: String
)