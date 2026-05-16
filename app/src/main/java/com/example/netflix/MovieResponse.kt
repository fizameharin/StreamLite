package com.example.netflix

data class MovieResponse(
    val results: List<MovieResult>
)

data class MovieResult(
    val poster_path: String,
    val title: String
)