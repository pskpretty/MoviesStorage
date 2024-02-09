package com.sample.moviesstorage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoviesStorageApplication

fun main(args: Array<String>) {
    runApplication<MoviesStorageApplication>(*args)
}
