package com.example.moviesbydany.features.movies.data.source.remote


import com.example.moviesbydany.core.api.RetrofitService
import com.example.moviesbydany.features.movies.domain.model.MovieDetails
import com.example.moviesbydany.features.movies.domain.model.MovieSearchResult
import io.reactivex.Single
import javax.inject.Inject


/**
 * This repository is responsible for
 * fetching data  from server
 * */
abstract class MoviesDataSource {
    abstract fun getMovies(name: String, index: Int): Single<MovieSearchResult>
    abstract fun getMovieDetails(id: Int): Single<MovieDetails>

}

class MoviesDataSourceImp @Inject constructor(
    private val retrofitService: RetrofitService
) : MoviesDataSource() {


    override fun getMovies(name: String, index: Int): Single<MovieSearchResult> {
        return retrofitService.getMovies()
    }

    override fun getMovieDetails(id: Int): Single<MovieDetails> {
        return retrofitService.getMovieDetails()
    }


}
