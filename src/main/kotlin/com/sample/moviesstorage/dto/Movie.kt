package com.sample.moviesstorage.dto

data class Movie(val name: String, val slot: List<MovieSlot>, val screen: Screen)

data class MovieSlot(val date: String, val time: String)

enum class Screen {
    Standard, IMAX
}