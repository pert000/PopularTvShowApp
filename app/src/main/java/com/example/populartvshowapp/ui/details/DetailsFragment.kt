package com.example.populartvshowapp.ui.details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.populartvshowapp.databinding.FragmentDetailsBinding
import com.example.populartvshowapp.model.CreatedBy
import com.example.populartvshowapp.ui.details.adapter.CreatorAdapter
import com.example.populartvshowapp.ui.details.viewmodel.DetailsViewModel
import com.example.spacexmp.utils.ExtraKeys
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    lateinit var adapterCreator: CreatorAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        viewModel.getSimilarTvShows(66922)

        arguments?.getInt(ExtraKeys.TV_SHOW_ID)?.let { viewModel.getDetails(it) };
        viewModel.detailsResponse.observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original//" + it.data?.poster_path)//todo
                .into(binding.image)

            binding.content.name.text = it.data?.name
            binding.content.tagLine.text = it.data?.tagline
            binding.content.overview.text = it.data?.overview
            binding.content.homepage.text = it.data?.homepage
            //    binding.content.creator.text = it.data?.created_by?.get(0)?.name //todo
            binding.content.voteAverage.text = it.data?.vote_average.toString()
            val list: List<CreatedBy>? = it.data?.created_by
            if (list != null) {
                adapterCreator = CreatorAdapter(
                    requireContext(),
                    list
                )
                binding.content.list.adapter = adapterCreator
                adapterCreator.notifyDataSetChanged()
            }
        })




        viewModel.similarResponse.observe(viewLifecycleOwner, Observer {
            it.data


        })

        return binding.root
    }


    private fun setupRecycler() {
        context?.apply {
            binding.content.recycler.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            //  binding.content.recycler.adapter = adapter
        }
    }
}