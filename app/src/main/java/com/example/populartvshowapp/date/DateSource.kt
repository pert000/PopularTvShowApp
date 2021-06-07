package com.example.populartvshowapp.date

import androidx.paging.PagingSource
import com.example.populartvshowapp.model.TvShowsModel
import com.example.spacexmp.utils.ExtraKeys
import javax.inject.Inject

class DateSource @Inject constructor(
    private val apiService: ApiService,
) : BaseDataSource() {
    suspend fun getDetails(id: Int)=getResult { apiService.getDetails(id,ExtraKeys.API_KEY,ExtraKeys.LANGUAGE) }
    suspend fun getSimilar(id: Int)=getResult { apiService.getSimilar(id,ExtraKeys.API_KEY,ExtraKeys.LANGUAGE) }
}