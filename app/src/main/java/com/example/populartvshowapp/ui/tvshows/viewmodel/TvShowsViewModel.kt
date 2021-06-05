package com.example.populartvshowapp.ui.tvshows.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.populartvshowapp.date.Resource
import com.example.populartvshowapp.model.TvShowsModel
import com.example.populartvshowapp.model.TvShowsResponse
import com.example.populartvshowapp.repository.TvShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val pagingRepository: TvShowsRepository
) : ViewModel() {
//    private var tvShowsRequest = MutableLiveData<String>()
//
//    var tvShowsResponse: LiveData<Resource<TvShowsResponse>> =
//        tvShowsRequest.switchMap { pagingRepository.getShows() }
//
//
//    fun getShips() {
//        tvShowsRequest.value= " "
//    }

    private var currentResult: Flow<PagingData<TvShowsModel>>? = null
    fun getShips(): Flow<PagingData<TvShowsModel>>{
        val result: Flow<PagingData<TvShowsModel>> = pagingRepository.getTvShow().cachedIn(viewModelScope)
        currentResult = result
        return result;
    }

}