package com.noorifytech.moviesapp.data.dao.backend.impl.retrofit

import com.noorifytech.moviesapp.common.AppConstants.Companion.API_KEY
import com.noorifytech.moviesapp.data.dao.backend.dto.MovieDto
import com.noorifytech.moviesapp.data.dao.backend.dto.MoviesListResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {
    @GET("movie/popular?api_key=$API_KEY")
    fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): Observable<Response<MoviesListResponse>>
}