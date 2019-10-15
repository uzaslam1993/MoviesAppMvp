package com.noorifytech.moviesapp.di

import com.noorifytech.moviesapp.application.MoviesAppMvp
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.data.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.data.dao.backend.impl.MoviesBackendDaoImpl
import com.noorifytech.moviesapp.data.dao.backend.impl.retrofit.RetrofitFactory
import com.noorifytech.moviesapp.data.dao.backend.impl.retrofit.TMDBApi
import com.noorifytech.moviesapp.data.dao.db.MoviesDBDao
import com.noorifytech.moviesapp.data.dao.db.RoomDB
import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.data.repository.impl.MoviesRepositoryImpl
import com.noorifytech.moviesapp.ui.usecase.GetPopularMoviesUseCase
import com.noorifytech.moviesapp.ui.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(
        moviesDBDao: MoviesDBDao,
        moviesBackendDao: MoviesBackendDao,
        mapper: MovieMapper
    ): MoviesRepository {
        return MoviesRepositoryImpl(moviesDBDao, moviesBackendDao, mapper)
    }

    @Provides
    fun provideBackendDaoImpl(): MoviesBackendDao {
        return MoviesBackendDaoImpl(
            RetrofitFactory.getDefaultRetrofit()
                .create(TMDBApi::class.java)
        )
    }

    @Provides
    fun provideMovieMapper(): MovieMapper {
        return MovieMapper
    }

    @Provides
    fun provideDbDaoImpl(): MoviesDBDao {
        return RoomDB.getInstance(MoviesAppMvp.instance!!.applicationContext).moviesDao()
    }
}