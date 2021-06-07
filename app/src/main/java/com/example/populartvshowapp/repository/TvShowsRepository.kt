package com.example.populartvshowapp.repository

import com.example.populartvshowapp.date.TvShowsPagingDataSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.populartvshowapp.model.SimilarResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TvShowsRepository @Inject constructor(
    private val tvShowsPagingDataSource: TvShowsPagingDataSource
) {
    fun getTvShow(): Flow<PagingData<SimilarResponse>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {tvShowsPagingDataSource}
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }

}







