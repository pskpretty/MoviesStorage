package com.sample.moviesstorage.entities

import jakarta.persistence.*

@Entity
data class MovieEntity(
    @Id @GeneratedValue var id: Long? = null,
    var name: String,
    var screen: String,
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "movie_entity_id")
    val slots: List<MovieSlotEntity> = arrayListOf()
)

