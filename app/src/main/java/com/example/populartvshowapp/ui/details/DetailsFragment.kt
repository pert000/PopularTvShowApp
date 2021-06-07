package com.example.populartvshowapp.ui.details


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.populartvshowapp.R
import com.example.populartvshowapp.databinding.FragmentDetailsBinding
import com.example.populartvshowapp.date.Resource
import com.example.populartvshowapp.model.CreatedBy
import com.example.populartvshowapp.ui.details.adapter.CreatorAdapter
import com.example.populartvshowapp.ui.details.adapter.DetailsAdapter
import com.example.populartvshowapp.ui.details.viewmodel.DetailsViewModel
import com.example.populartvshowapp.ui.tvshows.adapter.TvShowsAdapter
import com.example.spacexmp.utils.ExtraKeys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    lateinit var adapterCreator: CreatorAdapter
    private lateinit var adapter: DetailsAdapter
    private var job: Job? = null
    private var id:Int?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        id=arguments?.getInt(ExtraKeys.TV_SHOW_ID)

        arguments?.getInt(ExtraKeys.TV_SHOW_ID)?.let { viewModel.getDetails(it) };
        viewModel.detailsResponse.observe(viewLifecycleOwner, Observer {
            if (it.status == Resource.Status.SUCCESS) {
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/original//" + it.data?.poster_path)//todo
                    .into(binding.image)

                binding.content.name.text = it.data?.name
                binding.content.tagLine.text = it.data?.tagline
                binding.content.overview.text = it.data?.overview
                binding.content.homepage.text = it.data?.homepage
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
            } else if (it.status == Resource.Status.ERROR) {

                Toast.makeText(
                    context,
                    getString(R.string.error_text),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id=arguments?.getInt(ExtraKeys.TV_SHOW_ID)
        adapter= DetailsAdapter(requireContext())
        setupRecycler()
        getTvShow()
    }



    private fun getTvShow() {
        job?.cancel()
        job = lifecycleScope.launch {
            id?.let {
                viewModel.getSimilar(it).collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun setupRecycler() {
        context?.apply {
            binding.content.recycler.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.content.recycler.adapter = adapter
        }
    }
}