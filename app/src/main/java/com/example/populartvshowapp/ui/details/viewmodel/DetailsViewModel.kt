package com.example.populartvshowapp.ui.details.viewmodel

import androidx.lifecycle.*
import com.example.populartvshowapp.date.Resource
import com.example.populartvshowapp.model.DetailsResponse
import com.example.populartvshowapp.repository.DetailsRepository
import com.example.populartvshowapp.repository.TvShowsRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DetailsRepository
) : ViewModel() {
    private var detailsRequest = MutableLiveData<Int>()

    var detailsResponse: LiveData<Resource<DetailsResponse>> =
        detailsRequest.switchMap { repository.getDetails(it) }


    fun getDetails(id: Int) {
        detailsRequest.value = id
    }
}