package com.example.populartvshowapp.ui.details


import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.populartvshowapp.databinding.FragmentDetailsBinding
import com.example.populartvshowapp.model.CreatedBy
import com.example.populartvshowapp.ui.details.viewmodel.DetailsViewModel
import com.example.spacexmp.utils.ExtraKeys
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)


        arguments?.getInt(ExtraKeys.TV_SHOW_ID)?.let { viewModel.getDetails(it) };
        viewModel.detailsResponse.observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original//" + it.data?.poster_path)//todo
                .into(binding.image)

            binding.content.name.text = it.data?.name
            binding.content.tagLine.text = it.data?.tagline
            binding.content.overview.text = it.data?.overview
            binding.content.homepage.text=it.data?.homepage
            binding.content.creator.text = it.data?.created_by?.get(0)?.name //todo

        })



        return binding.root
    }


}