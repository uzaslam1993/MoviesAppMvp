package com.noorifytech.moviesapp.di

import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.ui.view.activity.MoviesListActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class, UseCaseModule::class, RepositoryModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MoviesListActivity)

}