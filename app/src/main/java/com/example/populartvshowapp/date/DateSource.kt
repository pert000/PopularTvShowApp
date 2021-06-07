package com.example.populartvshowapp.date

import com.example.populartvshowapp.utils.ExtraKeys
import javax.inject.Inject

class DateSource @Inject constructor(
    private val apiService: ApiService,
) : BaseDataSource() {
    suspend fun getDetails(id: Int)=getResult { apiService.getDetails(id,
        ExtraKeys.API_KEY,
        ExtraKeys.LANGUAGE) }

}