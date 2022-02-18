package com.example.moviesbydany.features.movies.domain.usecase


import com.example.moviesbydany.features.movies.core.TestConstants

import com.example.moviesbydany.features.movies.domain.repository.MoviesRepository
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class GetMoviesDetailsUseCaseTest {
    lateinit var getMoviesDetailsUseCase: GetMoviesDetailsUseCase

    @Mock
    lateinit var mockMoviesRepository: MoviesRepository


    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockMoviesRepository = Mockito.mock(MoviesRepository::class.java)
        getMoviesDetailsUseCase = GetMoviesDetailsUseCase(mockMoviesRepository)

    }

    @Test
    fun testGetMovieSuccess() {
        Mockito.doReturn(Single.just(TestConstants.movieDetails)).`when`(mockMoviesRepository)
            .getMovieDetails("a344sifit")
        var result = getMoviesDetailsUseCase.buildUseCaseSingle(Params(id = "a344sifit"))

        Assert.assertEquals(result.blockingGet(), TestConstants.movieDetails)

    }

    @Test
    fun testGetMovieFail() {
        Mockito.doReturn(Single.just(TestConstants.movieDetailsFailed)).`when`(mockMoviesRepository)
            .getMovieDetails("ft44")
        var result = getMoviesDetailsUseCase.buildUseCaseSingle(Params(id = "ft44"))
        Assert.assertEquals(result.blockingGet(), TestConstants.movieDetailsFailed)
    }
}