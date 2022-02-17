package com.example.moviesbydany.features.movies.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesbydany.features.movies.domain.model.MovieSearchResult
import com.example.moviesbydany.features.movies.domain.usecase.GetMoviesListUseCase
import com.example.moviesbydany.features.movies.domain.usecase.ListParams
import com.example.moviesbydany.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val getMoviesListUseCase: GetMoviesListUseCase) :
    ViewModel() {

    val response = MutableLiveData<Response<MovieSearchResult>>()
    fun getUsers(name: String, index: Int) {
        response.value = Response.loading(null)
        getMoviesListUseCase.execute(ListParams(name = name, index = index),
            onSuccess = {
                response.value = parseResponse(it)
            },
            onError = {
                response.value =
                    Response.error(data = null, message = it.message ?: "Error Occurred!")
            }
        )
    }

    private fun parseResponse(result: MovieSearchResult): Response<MovieSearchResult> {
        return if (result.Response.contentEquals("True") && result.Search.isNotEmpty()) {
            Response.success(data = result)
        } else {
            Response.error(data = null, message = "Movie not found!")
        }
    }
}