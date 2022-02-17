package com.example.moviesbydany.features.movies.domain.repository

import com.example.moviesbydany.features.movies.domain.model.MovieDetails
import com.example.moviesbydany.features.movies.domain.model.MovieSearchResult
import io.reactivex.Single


interface MoviesRepository {

    fun getMovies(name: String, index: Int): Single<MovieSearchResult>
    fun getMovieDetails(movieID: Int): Single<MovieDetails>
}