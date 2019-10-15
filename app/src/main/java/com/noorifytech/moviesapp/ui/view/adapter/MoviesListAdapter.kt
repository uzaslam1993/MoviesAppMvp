package com.noorifytech.moviesapp.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.ui.view.callback.MoviesListCellCallback
import kotlinx.android.synthetic.main.activity_movies_list_item.view.*

class MoviesListAdapter(
    private val context: Context,
    private val callback: MoviesListCellCallback,
    private var list: List<MovieVO>
) : RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>() {

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = list.get(position)
        holder.bind(context, movie)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val viewItem = LayoutInflater.from(context)
            .inflate(R.layout.activity_movies_list_item, parent, false)
        return MoviesViewHolder(viewItem, callback)
    }

    class MoviesViewHolder(
        private val viewItem: View,
        private val callback: MoviesListCellCallback
    ) : RecyclerView.ViewHolder(viewItem) {

        fun bind(context: Context, movie: MovieVO) {
            viewItem.movieNameTV.text = movie.name

            Glide.with(context)
                .asBitmap()
                .load(movie.imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(viewItem.movieImageIV)

            viewItem.setOnClickListener {
                callback.onMovieSelected(movie.id, adapterPosition)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}