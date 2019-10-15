package com.noorifytech.moviesapp

import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.ui.base.RxBaseProcessor
import com.noorifytech.moviesapp.ui.presenter.MoviesListPresenter
import com.noorifytech.moviesapp.ui.presenter.impl.MoviesListPresenterImpl
import com.noorifytech.moviesapp.ui.usecase.GetPopularMoviesUseCase
import com.noorifytech.moviesapp.ui.view.MoviesListView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.Exception

class MoviesListPresenterImplTest {

    @Mock
    lateinit var view: MoviesListView
    @Mock
    lateinit var useCase: GetPopularMoviesUseCase

    @Mock
    lateinit var presenter: MoviesListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MoviesListPresenterImpl(
            useCase,
            RxBaseProcessor(Schedulers.trampoline(), Schedulers.trampoline())
        )
        presenter.initView(view)
    }

    @Test
    fun onAttach_whenSuccess_showListIsCalled() {
        // Arrange
        val moviesList = listOf<MovieVO>()
        Mockito.`when`(useCase.getPopularMovies()).thenReturn(Observable.just(moviesList))

        // Act
        presenter.onAttach()

        // Assert
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showList(moviesList)
    }

    @Test
    fun onAttach_whenError_showListIsCalled() {
        // Arrange
        Mockito.`when`(useCase.getPopularMovies()).thenReturn(Observable.error(Exception()))

        // Act
        presenter.onAttach()

        // Assert
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showError()
    }
}