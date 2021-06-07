package com.example.populartvshowapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.populartvshowapp.date.SimilarPagingDataSource
import com.example.populartvshowapp.model.ResultX
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SimilarTvShowsRepository @Inject constructor(
    private val similarPagingDataSource: SimilarPagingDataSource
) {
    fun getTvShow(id: Int): Flow<PagingData<ResultX>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { similarPagingDataSource.apply { this.id = id} }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }

}







