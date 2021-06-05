package com.example.populartvshowapp.date

import javax.inject.Inject

class DateSource @Inject constructor(
    private val apiService: ApiService,
) : BaseDataSource() {
    suspend fun getDetails()=getResult { apiService.getDetails("a21b74375fa6e98953cb13979b219a6a","en-US",103157) }
}