package com.example.moviesbydany.features.movies.data.repository

import com.example.danytransfernow.feature.login.domain.model.User
import com.example.moviesbydany.features.movies.data.source.remote.MoviesDataSource
import com.example.moviesbydany.features.movies.domain.model.MovieDetails
import com.example.moviesbydany.features.movies.domain.model.MovieSearchResult
import com.example.moviesbydany.features.movies.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject


/**
 * This repository is responsible for
 * fetching data[User] from server or db
 * */
class MovieRepositoryImp @Inject constructor(
    private val moviesDataSource: MoviesDataSource
) :
    MoviesRepository {

    override fun getMovies(name: String, index: Int): Single<MovieSearchResult> {
        TODO("Not yet implemented")
    }

    override fun getMovieDetails(movieID: Int): Single<MovieDetails> {
        TODO("Not yet implemented")
    }


}