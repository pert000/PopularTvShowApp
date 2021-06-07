package com.example.populartvshowapp.date

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.populartvshowapp.model.ResultX
import com.example.populartvshowapp.utils.ExtraKeys
import javax.inject.Inject


class SimilarPagingDataSource @Inject constructor(
    private val apiService: ApiService,
) : PagingSource<Int, ResultX>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
    var id: Int? = null

    override fun getRefreshKey(state: PagingState<Int, ResultX>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultX> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response =
                apiService.getSimilar(id, ExtraKeys.API_KEY, ExtraKeys.LANGUAGE, page)
            val shows = mutableListOf<ResultX>()

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