package com.example.moviesbydany.features.movies.presentation.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesbydany.features.movies.domain.model.MovieDetails
import com.example.moviesbydany.features.movies.domain.usecase.GetMoviesDetailsUseCase
import com.example.moviesbydany.features.movies.domain.usecase.Params
import com.example.moviesbydany.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMoviesDetailsViewModel: GetMoviesDetailsUseCase) :
    ViewModel() {
    val movie: ObservableField<MovieDetails> = ObservableField()
    val response = MutableLiveData<Response<MovieDetails>>()
    fun getMovieDetail(id: String) {
        response.value = Response.loading(null)
        getMoviesDetailsViewModel.execute(
            Params(id = id),
            onSuccess = {
                movie.set(it)
                response.value = Response.success(data = it)
            },
            onError = {
                response.value =
                    Response.error(data = null, message = it.message ?: "Error Occurred!")
            }
        )
    }
}