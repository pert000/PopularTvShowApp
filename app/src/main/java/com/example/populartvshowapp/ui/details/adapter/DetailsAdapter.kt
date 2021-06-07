package com.example.populartvshowapp.ui.details.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.populartvshowapp.R
import com.example.populartvshowapp.databinding.SimilarTvShowItemBinding
import com.example.populartvshowapp.model.ResultX
import com.example.populartvshowapp.utils.ExtraKeys


class DetailsAdapter(val context: Context) :
    PagingDataAdapter<ResultX, DetailsAdapter.ViewHolder>(DiffCallback()) {


    class ViewHolder(
        val context: Context,
        private val binding: SimilarTvShowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ResultX) {
            binding.apply {
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/original//" +item.poster_path)
                    .into(binding.image)

                main.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putInt(ExtraKeys.TV_SHOW_ID, item.id)
                    Navigation.findNavController(it)
                        .navigate(R.id.action_detailsFragment_self, bundle)

                }

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
            SimilarTvShowItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }


}

private class DiffCallback : DiffUtil.ItemCallback<ResultX>() {
    override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem == newItem
    }
}