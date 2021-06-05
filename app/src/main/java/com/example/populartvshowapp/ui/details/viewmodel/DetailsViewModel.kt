package com.example.populartvshowapp.ui.details.viewmodel

import androidx.lifecycle.*
import com.example.populartvshowapp.date.Resource
import com.example.populartvshowapp.model.DetailsResponse
import com.example.populartvshowapp.repository.TvShowsRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: TvShowsRepository
) : ViewModel() {
    private var detailsRequest = MutableLiveData<String>()

//    var detailsResponse: LiveData<Resource<DetailsResponse>> =
//        detailsRequest.switchMap { repository.getDetails() }


//    fun getDetails() {
//        detailsRequest.value = " "
//    }
}