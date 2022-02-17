package com.example.moviesbydany.features.movies.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbydany.databinding.MovieListItemBinding
import com.example.moviesbydany.features.movies.domain.model.Movie
import com.squareup.picasso.Picasso


class MoviesRecyclerViewAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder>() {
    private var movieList = mutableListOf<Movie>()

    fun setList(list: List<Movie>, isReset: Boolean = false) {
        if (isReset)
            this.movieList.clear()
        if (!list.isNullOrEmpty())
            this.movieList.addAll(list)
        this.movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movieList[position]
        holder.contentView.text = item.Title
        Picasso.get()
            .load(item.Poster)
            .into(holder.imageView)
        holder.imageView.setOnClickListener {
            onClick.invoke(item.imdbID)
        }


    }

    override fun getItemCount(): Int = movieList.size

    inner class ViewHolder(binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView: AppCompatImageView = binding.ivBaner
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}