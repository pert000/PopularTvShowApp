package com.example.populartvshowapp.ui.details


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.example.populartvshowapp.databinding.FragmentDetailsBinding
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

        })

        return binding.root
    }


}