package com.example.populartvshowapp.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.populartvshowapp.R
import com.example.populartvshowapp.databinding.FragmentTvShowsBinding


class TvShowsFragment : Fragment() {
    private lateinit var binding: FragmentTvShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowsBinding.inflate(inflater, container, false);
        binding.title.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_tvShowsFragment_to_detailsFragment) }

        return binding.root
    }


}