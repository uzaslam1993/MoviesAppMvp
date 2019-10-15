package com.noorifytech.moviesapp.ui.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.di.ActivityModule
import com.noorifytech.moviesapp.di.DaggerActivityComponent
import com.noorifytech.moviesapp.ui.presenter.MoviesListPresenter
import com.noorifytech.moviesapp.ui.view.MoviesListView
import com.noorifytech.moviesapp.ui.view.adapter.MoviesListAdapter
import com.noorifytech.moviesapp.ui.view.callback.MoviesListCellCallback
import kotlinx.android.synthetic.main.activity_movies_list.*
import javax.inject.Inject

class MoviesListActivity : AppCompatActivity(), MoviesListView, MoviesListCellCallback {

    private lateinit var moviesListAdapter: MoviesListAdapter

    @Inject
    lateinit var presenter: MoviesListPresenter

    private var moviesList: ArrayList<MovieVO> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_list)
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDetach()
    }

//    =======================  MoviesListView Implementation =======================

    override fun showList(list: List<MovieVO>) {
        moviesList.addAll(list)
        moviesListAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showNoContent() {
        Snackbar.make(
            movies_list_root,
            R.string.error_no_content_message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showNoConnection() {
        Snackbar.make(
            movies_list_root,
            R.string.no_internet,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showError() {
        Snackbar.make(
            movies_list_root,
            R.string.error_generic_message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onMovieSelected(movieId: Int, position: Int) {

    }

    private fun init() {
        presenter.initView(this)
        presenter.onAttach()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        moviesListRV.layoutManager = LinearLayoutManager(this)
        moviesListRV.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        moviesListAdapter = MoviesListAdapter(this, this, moviesList)
        moviesListRV.adapter = moviesListAdapter
    }
}