package com.example.moviesbydany.features.movies.domain.model

data class MovieSearchResult(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)