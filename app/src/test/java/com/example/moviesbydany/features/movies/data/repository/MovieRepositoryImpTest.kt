package com.example.moviesbydany.features.movies.data.repository

import com.example.moviesbydany.core.api.RetrofitService
import com.example.moviesbydany.features.movies.core.TestConstants
import com.example.moviesbydany.features.movies.data.source.remote.MoviesDataSource
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImpTest {
    lateinit var movieRepositoryImp: MovieRepositoryImp

    @Mock
    lateinit var mockMoviesDataSource: MoviesDataSource

    @Mock
    lateinit var mockRetrofitService: RetrofitService


    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockRetrofitService = Mockito.mock(RetrofitService::class.java)
        mockMoviesDataSource = Mockito.mock(MoviesDataSource::class.java)
        movieRepositoryImp = MovieRepositoryImp(mockMoviesDataSource)


    }

    @Test
    fun testGetMoviesSuccess() {
        Mockito.doReturn(Single.just(TestConstants.movieSearchSuccess)).`when`(mockMoviesDataSource)
            .getMovies("Marvel", 2)
        var result = movieRepositoryImp.getMovies("Marvel", 2)
        assertEquals(result.blockingGet(), TestConstants.movieSearchSuccess)
    }

    @Test
    fun testGetMoviesFail() {
        Mockito.doReturn(Single.just(TestConstants.movieSearchFail)).`when`(mockMoviesDataSource)
            .getMovies("", 20)
        var result = movieRepositoryImp.getMovies("", 20)
        assertEquals(result.blockingGet(), TestConstants.movieSearchFail)
    }

    @Test
    fun testGetMovieDetailsSuccess() {
        Mockito.doReturn(Single.just(TestConstants.movieDetails)).`when`(mockMoviesDataSource)
            .getMovieDetails("tt4154664")
        var result = movieRepositoryImp.getMovieDetails("tt4154664")
        assertEquals(result.blockingGet(), TestConstants.movieDetails)
    }

    @Test
    fun testGetMoviesDetailsFail() {
        Mockito.doReturn(Single.just(TestConstants.movieDetailsFailed)).`when`(mockMoviesDataSource)
            .getMovieDetails("tt415")
        var result = movieRepositoryImp.getMovieDetails("tt415")
        assertEquals(result.blockingGet(), TestConstants.movieDetailsFailed)
    }
}