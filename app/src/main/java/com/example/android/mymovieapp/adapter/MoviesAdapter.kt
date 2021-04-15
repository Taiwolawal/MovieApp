package com.example.android.mymovieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.android.mymovieapp.databinding.ItemMovieBinding
import com.example.android.mymovieapp.model.Movies

class MoviesAdapter(
    private var movies: MutableList<Movies>,
    private val onMovieClick: (movie: Movies) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
            holder.bind(movies[position])
    }

    fun appendMovies(movies: List<Movies>){
        this.movies.addAll(movies)
        notifyItemRangeChanged(this.movies.size, movies.size - 1)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movies.size

//    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
     inner class MovieViewHolder(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
//        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)
    private val poster: ImageView = binding.itemMoviePoster
        fun bind(movie: Movies) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(poster)

            itemView.setOnClickListener {
                onMovieClick.invoke(movie)
            }

        }

    }

}