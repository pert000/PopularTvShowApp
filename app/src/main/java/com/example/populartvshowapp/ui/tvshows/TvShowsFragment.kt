package com.example.populartvshowapp.ui.tvshows

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.populartvshowapp.R
import com.example.populartvshowapp.databinding.FragmentTvShowsBinding
import com.example.populartvshowapp.ui.tvshows.adapter.TvShowsAdapter
import com.example.populartvshowapp.ui.tvshows.viewmodel.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvShowsFragment : Fragment() {
    private lateinit var binding: FragmentTvShowsBinding
    private lateinit var adapter: TvShowsAdapter
    private  val viewModel: TvShowsViewModel by viewModels()
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowsBinding.inflate(inflater, container, false);

        binding.refreshLayout.setOnRefreshListener {
            getTvShow()
        }


        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter= TvShowsAdapter(requireContext())
        setupRecycler()
        getTvShow()
    }
    private fun setupRecycler() {
        context?.apply {
            binding.recycler.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            binding.recycler.adapter = adapter
        }
    }

    private fun getTvShow() {

       job?.cancel()
       job = lifecycleScope.launch {
            viewModel.getShips().collectLatest {
                adapter.submitData(it)
            }
        }
       job!!.invokeOnCompletion {
            binding.refreshLayout.isRefreshing = false

        }

    }

}