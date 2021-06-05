package com.example.populartvshowapp.date

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.populartvshowapp.model.TvShowsModel
import com.example.populartvshowapp.repository.TvShowsRepository
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 0

class TvShowsPagingDataSource @Inject constructor(
    private val apiService: ApiService,
) : BaseDataSource() {

    suspend fun getTvShows()=getResult { apiService.getTvShows("a21b74375fa6e98953cb13979b219a6a","en-US",10) }


    suspend fun getDetails()=getResult { apiService.getDetails("a21b74375fa6e98953cb13979b219a6a","en-US",103157) }


//    override fun getRefreshKey(state: PagingState<Int, TvShowsModel>): Int? {
//        TODO("Not yet implemented")
//    }

//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShowsModel> {
//        val page = params.key ?: STARTING_PAGE_INDEX
//        return try {
//            val response =
//                apiService.getTvShows("a21b74375fa6e98953cb13979b219a6a", "en-US", page)
//            val shows = mutableListOf<TvShowsModel>()
//
//            response.body()?.results?.let {
//                shows.addAll(it)
//            }
//
//            LoadResult.Page(
//                data = shows,
//                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
//                nextKey = if (page == response.body()?.total_pages) null else page + 1
//            )
//        } catch (exception: Exception) {
//            LoadResult.Error(exception)
//        }
//    }

}