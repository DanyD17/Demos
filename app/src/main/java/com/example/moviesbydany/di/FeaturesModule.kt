package com.example.moviesbydany.di

import com.example.moviesbydany.core.api.RetrofitService
import com.example.moviesbydany.features.movies.data.repository.MovieRepositoryImp
import com.example.moviesbydany.features.movies.data.source.remote.MoviesDataSource
import com.example.moviesbydany.features.movies.data.source.remote.MoviesDataSourceImp
import com.example.moviesbydany.features.movies.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FeaturesModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(
        moviesDataSource: MoviesDataSource
    ): MoviesRepository {
        return MovieRepositoryImp(moviesDataSource)
    }

    @Singleton
    @Provides
    fun provideMoviesDataSource(
        retrofitService: RetrofitService
    ): MoviesDataSource {
        return MoviesDataSourceImp(retrofitService)
    }
}