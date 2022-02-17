package com.example.moviesbydany.core.api

import com.example.moviesbydany.features.movies.domain.model.MovieDetails
import com.example.moviesbydany.features.movies.domain.model.MovieSearchResult
import com.example.moviesbydany.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/")
    fun getMovies(
        @Query("apikey") apiKey: String = Constants.apikey,
        @Query("s") name: String,
        @Query("page") pageNo: Int,
        @Query("type") type: String = Constants.type
    ): Single<MovieSearchResult>


    @GET("/")
    fun getMovieDetails(
        @Query("apikey") apiKey: String = Constants.apikey,
        @Query("i") id: String
    ): Single<MovieDetails>

}