package com.example.populartvshowapp.ui.tvshows.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.populartvshowapp.databinding.ShipsItemBinding
import com.example.populartvshowapp.model.TvShowsModel

class TvShowsAdapter(val context: Context) :
    PagingDataAdapter<TvShowsModel, TvShowsAdapter.ViewHolder>(DiffCallback()) {


    class ViewHolder(
        val context: Context,
        private val binding: ShipsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TvShowsModel) {
            binding.apply {
                title.text = item.name
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            context,
            ShipsItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }


}

private class DiffCallback : DiffUtil.ItemCallback<TvShowsModel>() {
    override fun areItemsTheSame(oldItem: TvShowsModel, newItem: TvShowsModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TvShowsModel, newItem: TvShowsModel): Boolean {
        return oldItem == newItem
    }
}