package com.sample.moviesstorage.dto

data class Movie(val name: String, val slot: List<MovieSlot>, val screen: String)

data class MovieSlot(val date: String, val time: String)
