package com.example.moviesbydany.features.movies.data.source.remote

import com.example.moviesbydany.core.api.RetrofitService
import com.example.moviesbydany.features.movies.core.TestConstants
import io.reactivex.Single
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class MoviesDataSourceTest {
    lateinit var moviesDataSource: MoviesDataSource

    @Mock
    lateinit var mockRetrofitService: RetrofitService


    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockRetrofitService = Mockito.mock(RetrofitService::class.java)
        moviesDataSource = MoviesDataSourceImp(mockRetrofitService)


    }

    @Test
    fun testGetMoviesSuccess() {
        Mockito.doReturn(Single.just(TestConstants.movieSearchSuccess)).`when`(mockRetrofitService)
            .getMovies(name = "Marvel", pageNo = 2)
        var result = moviesDataSource.getMovies("Marvel", 2)
        assertEquals(result.blockingGet(), TestConstants.movieSearchSuccess)
    }

    @Test
    fun testGetMoviesFail() {
        Mockito.doReturn(Single.just(TestConstants.movieSearchFail)).`when`(mockRetrofitService)
            .getMovies(name = "", pageNo = 2)
        var result = moviesDataSource.getMovies("", 2)
        assertEquals(result.blockingGet(), TestConstants.movieSearchFail)
    }

    @Test
    fun testGetMovieDetailsSuccess() {
        Mockito.doReturn(Single.just(TestConstants.movieDetails)).`when`(mockRetrofitService)
            .getMovieDetails(id = "tt4154664")
        var result = moviesDataSource.getMovieDetails("tt4154664")
        assertEquals(result.blockingGet(), TestConstants.movieDetails)
    }

    @Test
    fun testGetMoviesDetailsFail() {
        Mockito.doReturn(Single.just(TestConstants.movieDetailsFailed)).`when`(mockRetrofitService)
            .getMovieDetails(id = "tt4154664")
        var result = moviesDataSource.getMovieDetails("tt4154664")
        assertEquals(result.blockingGet(), TestConstants.movieDetailsFailed)
    }
}