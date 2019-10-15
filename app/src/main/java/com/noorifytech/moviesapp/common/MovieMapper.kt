package com.noorifytech.moviesapp.common

import android.annotation.SuppressLint
import com.noorifytech.moviesapp.data.dao.backend.dto.MovieDto
import com.noorifytech.moviesapp.data.dao.backend.dto.MoviesListResponse
import com.noorifytech.moviesapp.data.dao.db.entity.MovieEntity
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import java.text.SimpleDateFormat

object MovieMapper {
    fun toMovies(moviesResponse: MoviesListResponse): List<MovieVO> {
        val movies = moviesResponse.results

        return movies.map {
            MovieVO(it.id, it.title, it.getPosterFullPath(), moviesResponse.page)
        }
    }
}