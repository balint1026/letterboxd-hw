package com.balint1026.tellhw.models

data class Review(
    val userId: String,
    val movieId: Int,
    val rating: Double,
    val reviewText: String,
    val timestamp: Long
)