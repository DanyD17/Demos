package com.example.moviesbydany.features.movies.domain.usecase


import com.example.moviesbydany.core.useCase.SingleUseCase
import com.example.moviesbydany.features.movies.domain.model.MovieSearchResult
import com.example.moviesbydany.features.movies.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject


class GetMoviesListUseCase @Inject constructor(private val repository: MoviesRepository) :
    SingleUseCase<MovieSearchResult, ListParams>() {

    override fun buildUseCaseSingle(params: ListParams): Single<MovieSearchResult> {
        return repository.getMovies(name = params.name, index = params.index)
    }
}

data class ListParams(
    var name: String,
    var index: Int = 1
)