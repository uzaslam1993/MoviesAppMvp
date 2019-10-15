package com.noorifytech.moviesapp.ui.usecase

import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import io.reactivex.Observable

interface GetPopularMoviesUseCase {
    fun getPopularMovies(): Observable<List<MovieVO>>
}

class GetPopularMoviesUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetPopularMoviesUseCase {

    override fun getPopularMovies(): Observable<List<MovieVO>> =
        moviesRepository.getPopularMovies()
}