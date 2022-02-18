package com.example.moviesbydany.features.movies.domain.usecase


import com.example.moviesbydany.features.movies.core.TestConstants

import com.example.moviesbydany.features.movies.domain.repository.MoviesRepository
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class GetMoviesListUseCaseTest {
    lateinit var getMoviesListUseCase: GetMoviesListUseCase

    @Mock
    lateinit var mockMoviesRepository: MoviesRepository


    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockMoviesRepository = Mockito.mock(MoviesRepository::class.java)
        getMoviesListUseCase = GetMoviesListUseCase(mockMoviesRepository)

    }

    @Test
    fun testGetMoviesSuccess() {
        Mockito.doReturn(Single.just(TestConstants.movieSearchSuccess)).`when`(mockMoviesRepository)
            .getMovies("Marvel", 2)
        var result = getMoviesListUseCase.buildUseCaseSingle(ListParams(name = "Marvel", index = 2))

        Assert.assertEquals(result.blockingGet(), TestConstants.movieSearchSuccess)

    }

    @Test
    fun testGetMoviesFail() {
        Mockito.doReturn(Single.just(TestConstants.movieSearchFail)).`when`(mockMoviesRepository)
            .getMovies("", 2)
        var result = getMoviesListUseCase.buildUseCaseSingle(ListParams(name = "", index = 2))
        Assert.assertEquals(result.blockingGet(), TestConstants.movieSearchFail)
    }
}