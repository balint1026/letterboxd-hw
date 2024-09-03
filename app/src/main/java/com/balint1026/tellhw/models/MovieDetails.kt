package com.balint1026.tellhw.models

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double
)
