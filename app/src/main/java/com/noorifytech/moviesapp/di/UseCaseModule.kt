package com.noorifytech.moviesapp.di

import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.ui.usecase.GetPopularMoviesUseCase
import com.noorifytech.moviesapp.ui.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideUseCase(moviesRepository: MoviesRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(moviesRepository)
    }
}