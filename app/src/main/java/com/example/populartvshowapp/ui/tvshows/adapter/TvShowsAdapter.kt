package com.example.populartvshowapp.ui.tvshows.adapter

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
import com.example.populartvshowapp.databinding.TvShowItemBinding
import com.example.populartvshowapp.model.SimilarResponse
import com.example.populartvshowapp.utils.ExtraKeys

class TvShowsAdapter(val context: Context) :
    PagingDataAdapter<SimilarResponse, TvShowsAdapter.ViewHolder>(DiffCallback()) {


    class ViewHolder(
        val context: Context,
        private val binding: TvShowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SimilarResponse) {
            binding.apply {
                title.text = item.name
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/original//" + item.poster_path)//todo
                    .into(image)
                voteAverage.text = item.vote_average.toString()
                firstAirDate.text = item.first_air_date
                overview.text = item.overview

                itemMain.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putInt(ExtraKeys.TV_SHOW_ID, item.id)
                    Navigation.findNavController(it)
                        .navigate(R.id.action_tvShowsFragment_to_detailsFragment, bundle)

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
            TvShowItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }


}


private class DiffCallback : DiffUtil.ItemCallback<SimilarResponse>() {
    override fun areItemsTheSame(oldItem: SimilarResponse, newItem: SimilarResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SimilarResponse, newItem: SimilarResponse): Boolean {
        return oldItem == newItem
    }
}