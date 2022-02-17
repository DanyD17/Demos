package com.example.moviesbydany.core.api

import com.example.moviesbydany.features.movies.domain.model.MovieDetails
import com.example.moviesbydany.features.movies.domain.model.MovieSearchResult
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitService {

    @GET("")
    fun getMovies(): Single<MovieSearchResult>

    @GET("")
    fun getMovieDetails(): Single<MovieDetails>

}