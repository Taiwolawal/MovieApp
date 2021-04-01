package com.example.android.mymovieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.android.mymovieapp.R
import com.example.android.mymovieapp.databinding.ItemTvShowBinding
import com.example.android.mymovieapp.model.TvShow

class TvShowsAdapter(
    private  var tvShows: MutableList<TvShow>,
    private val onTvShowClick: (tvShow: TvShow) -> Unit
): RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder>() {

    inner class TvShowViewHolder(binding: ItemTvShowBinding): RecyclerView.ViewHolder(binding.root){
        private val poster: ImageView = binding.itemTvShowPoster
        fun bind(tvShow: TvShow){
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${tvShow.posterPath}")
                .transform(CenterCrop())
                .into(poster)
            itemView.setOnClickListener {
                onTvShowClick.invoke(tvShow)
            }
        }
    }

    fun appendTvShows(tvShow: List<TvShow>){
        this.tvShows.addAll(tvShow)
        notifyItemRangeChanged(this.tvShows.size, tvShow.size -1)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int = tvShows.size
}