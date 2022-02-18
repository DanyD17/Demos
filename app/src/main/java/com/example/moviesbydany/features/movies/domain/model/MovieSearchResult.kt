package com.example.moviesbydany.features.movies.domain.model

data class MovieSearchResult(
    val Response: String,
    var Search: List<Movie>?,
    val totalResults: String?
)