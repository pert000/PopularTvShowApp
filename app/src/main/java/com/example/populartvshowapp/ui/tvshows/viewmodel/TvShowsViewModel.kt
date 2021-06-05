package com.example.populartvshowapp.ui.tvshows.viewmodel

import androidx.lifecycle.*
import com.example.populartvshowapp.date.Resource
import com.example.populartvshowapp.model.TvShowsResponse
import com.example.populartvshowapp.repository.TvShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val pagingRepository: TvShowsRepository
) : ViewModel() {
    private var tvShowsRequest = MutableLiveData<String>()

    var tvShowsResponse: LiveData<Resource<TvShowsResponse>> =
        tvShowsRequest.switchMap { pagingRepository.getShows() }


    fun getShips() {
        tvShowsRequest.value= " "
    }

//    private var ships: Flow<PagingData<TvShowsModel>>? = null
//
//    fun getShips(): Flow<PagingData<TvShowsModel>> {
//        val newResult: Flow<PagingData<TvShowsModel>> =
//            pagingRepository.getShips().cachedIn(viewModelScope)
//        ships = newResult
//        return newResult
//    }

}