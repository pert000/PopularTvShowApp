package com.example.populartvshowapp.repository

import com.example.populartvshowapp.date.DateSource
import com.example.populartvshowapp.date.TvShowsPagingDataSource
import com.example.spacexmp.utils.performGetOperation
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val dateSource: DateSource
) {
        fun getDetails(id:Int) = performGetOperation (networkCall = {
            dateSource.getDetails(id)
    })

}