package com.noorifytech.moviesapp.di

import android.app.Activity
import com.noorifytech.moviesapp.ui.base.RxBaseProcessor
import com.noorifytech.moviesapp.ui.presenter.MoviesListPresenter
import com.noorifytech.moviesapp.ui.presenter.impl.MoviesListPresenterImpl
import com.noorifytech.moviesapp.ui.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(getPopularMoviesUseCase: GetPopularMoviesUseCase, rxBaseProcessor: RxBaseProcessor):
            MoviesListPresenter {
        return MoviesListPresenterImpl(getPopularMoviesUseCase, rxBaseProcessor)
    }

    @Provides
    fun provideRxProcessor(): RxBaseProcessor {
        return RxBaseProcessor()
    }


}