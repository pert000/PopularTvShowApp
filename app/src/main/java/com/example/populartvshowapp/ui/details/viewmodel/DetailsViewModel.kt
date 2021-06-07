package com.example.populartvshowapp.ui.details.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.populartvshowapp.date.Resource
import com.example.populartvshowapp.model.*
import com.example.populartvshowapp.repository.DetailsRepository
import com.example.populartvshowapp.repository.SimilarTvShowsRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(

    private val repository: DetailsRepository,
    private val similarTvShowsRepository: SimilarTvShowsRepository
) : ViewModel() {
    private var detailsRequest = MutableLiveData<Int>()

    var detailsResponse: LiveData<Resource<DetailsResponse>> =
        detailsRequest.switchMap { repository.getDetails(it) }


    fun getDetails(id: Int) {
        detailsRequest.value = id
    }


    private var currentResult: Flow<PagingData<ResultX>>? = null
    fun getSimilar(id: Int): Flow<PagingData<ResultX>> {
        val result: Flow<PagingData<ResultX>> =
            similarTvShowsRepository.getTvShow(id).cachedIn(viewModelScope)
        currentResult = result
        return result;
    }
}