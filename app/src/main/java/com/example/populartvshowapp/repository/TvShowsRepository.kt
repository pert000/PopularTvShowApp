package com.example.populartvshowapp.repository

import com.example.populartvshowapp.date.TvShowsPagingDataSource
import com.example.spacexmp.utils.performGetOperation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class TvShowsRepository @Inject constructor(
    private val tvShowsPagingDataSource: TvShowsPagingDataSource
) {
//    fun getShips(): Flow<PagingData<TvShowsModel>> {
//        return Pager(
//            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
//            pagingSourceFactory = {tvShowsPagingDataSource}
//        ).flow
//    }
//
//    companion object {
//        private const val NETWORK_PAGE_SIZE = 25
//    }

    fun getShows() = performGetOperation (networkCall = {
        tvShowsPagingDataSource.getTvShows()
    })

    fun getDetails() = performGetOperation (networkCall = {
        tvShowsPagingDataSource.getDetails()
    })
}








//    companion object {
//        @Volatile
//        var instance: TvShowsRepository? = null
//
//        @Synchronized
//        fun getInstance(context: Context): TvShowsRepository? {
//            if (instance == null) {
//                instance = TvShowsRepository(context)
//            }
//            return instance
//        }
//    }
//}