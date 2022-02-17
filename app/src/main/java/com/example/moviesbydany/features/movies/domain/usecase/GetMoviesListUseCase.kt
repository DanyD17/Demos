package com.example.danytransfernow.feature.login.domain.usecase


import com.example.moviesbydany.core.useCase.SingleUseCase
import com.example.moviesbydany.features.movies.domain.model.MovieSearchResult
import com.example.moviesbydany.features.movies.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject


class GetMoviesListUseCase @Inject constructor(private val repository: MoviesRepository) :
    SingleUseCase<MovieSearchResult, Params>() {

    override fun buildUseCaseSingle(params: Params): Single<MovieSearchResult> {
        return repository.getMovies(name = params.name, index = params.index)
    }
}

data class Params(
    var name: String,
    var index: Int
)