package com.example.populartvshowapp.date

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.populartvshowapp.model.SimilarResponse
import com.example.populartvshowapp.repository.TvShowsRepository
import com.example.spacexmp.utils.ExtraKeys
import javax.inject.Inject


class TvShowsPagingDataSource @Inject constructor(
    private val apiService: ApiService,
) : PagingSource<Int, SimilarResponse>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, SimilarResponse>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimilarResponse> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response =
                apiService.getTvShows(ExtraKeys.API_KEY, ExtraKeys.LANGUAGE, page)
            val shows = mutableListOf<SimilarResponse>()

            response.body()?.results?.let {
                shows.addAll(it)
            }

            LoadResult.Page(
                data = shows,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.body()?.total_pages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}