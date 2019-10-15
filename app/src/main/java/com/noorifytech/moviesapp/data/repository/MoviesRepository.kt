package com.noorifytech.moviesapp.data.repository

import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import io.reactivex.Observable

interface MoviesRepository {
    fun getPopularMovies(): Observable<List<MovieVO>>
}