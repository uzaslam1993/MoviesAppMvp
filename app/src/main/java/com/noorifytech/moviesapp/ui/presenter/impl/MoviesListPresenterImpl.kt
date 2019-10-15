package com.noorifytech.moviesapp.ui.presenter.impl

import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.ui.base.BasePresenterImpl
import com.noorifytech.moviesapp.ui.base.RxBaseProcessor
import com.noorifytech.moviesapp.ui.presenter.MoviesListPresenter
import com.noorifytech.moviesapp.ui.usecase.GetPopularMoviesUseCase
import com.noorifytech.moviesapp.ui.view.MoviesListView
import io.reactivex.observers.DisposableObserver

class MoviesListPresenterImpl(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    rxBaseProcessor: RxBaseProcessor
) :
    BasePresenterImpl<MoviesListView>(rxBaseProcessor), MoviesListPresenter {

    override fun onAttach() {

        view.showLoading()
        val callback = object : DisposableObserver<List<MovieVO>>() {
            override fun onNext(pagedList: List<MovieVO>) {

                view.hideLoading()
                view.showList(pagedList)
            }

            override fun onError(e: Throwable) {

                view.hideLoading()
                view.showError()
            }

            override fun onComplete() {

            }

        }

        this.baseProcessor.processList(
            business = getPopularMoviesUseCase.getPopularMovies(),
            callback = callback
        )
    }
}
