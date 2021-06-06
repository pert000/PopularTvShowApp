package com.example.populartvshowapp.ui.details.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.populartvshowapp.R
import com.example.populartvshowapp.databinding.ProductDetailsContentBinding
import com.example.populartvshowapp.databinding.TvShowItemBinding
import com.example.populartvshowapp.model.TvShowsModel
import com.example.spacexmp.utils.ExtraKeys


class TvShowsAdapter(val context: Context) :
    PagingDataAdapter<TvShowsModel, TvShowsAdapter.ViewHolder>(DiffCallback()) {


    class ViewHolder(
        val context: Context,
        private val binding: ProductDetailsContentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TvShowsModel) {
            binding.apply {
                name.text = item.name

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
            ProductDetailsContentBinding.inflate(LayoutInflater.from(context), parent, false)
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