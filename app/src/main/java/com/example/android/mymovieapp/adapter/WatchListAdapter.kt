package com.example.android.mymovieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.android.mymovieapp.databinding.ItemWatchListBinding
import com.example.android.mymovieapp.model.WatchList

class WatchListAdapter(
    private var items: List<WatchList>,
    private val onItemClick: (item: WatchList) -> Unit
): RecyclerView.Adapter<WatchListAdapter.WatchListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListHolder {
        val binding = ItemWatchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WatchListHolder(binding)
    }

    override fun onBindViewHolder(holder: WatchListHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems (items: List<WatchList>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    inner class WatchListHolder(binding: ItemWatchListBinding): RecyclerView.ViewHolder(binding.root){
        private val poster: ImageView = binding.itemWatchListPoster
        fun bind(item: WatchList){
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${item.posterPath}")
                .transform(CenterCrop())
                .into(poster)

            itemView.setOnClickListener { onItemClick.invoke(item) }
        }

    }
}