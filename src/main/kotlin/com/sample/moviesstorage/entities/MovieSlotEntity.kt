package com.sample.moviesstorage.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
data class MovieSlotEntity(
    @Id @GeneratedValue var id: Long? = null,
    var date: String,
    var time: String
)