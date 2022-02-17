package com.example.moviesbydany.features.movies.domain.usecase

import com.example.moviesbydany.core.useCase.SingleUseCase
import com.example.moviesbydany.features.movies.domain.model.MovieDetails
import com.example.moviesbydany.features.movies.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject


class GetMoviesDetailsUseCase @Inject constructor(private val repository: MoviesRepository) :
    SingleUseCase<MovieDetails, Params>() {

    override fun buildUseCaseSingle(params: Params): Single<MovieDetails> {
        return repository.getMovieDetails(movieID = params.id)
    }
}

data class Params(
    var id: String
)