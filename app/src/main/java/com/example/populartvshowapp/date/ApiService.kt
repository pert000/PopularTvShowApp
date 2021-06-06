package com.example.populartvshowapp.date

import com.example.populartvshowapp.model.DetailsResponse
import com.example.populartvshowapp.model.TvShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/3/tv/popular")
    suspend fun getTvShows(
        @Query("api_key") api_key: String, @Query("language") language: String,
        @Query("page") page: Int
    ): Response<TvShowsResponse>

    @GET("/3/tv/{tv_id}")
    suspend fun getDetails(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Response<DetailsResponse>

    @GET("/3/tv/{tv_id}/similar")
    suspend fun getSimilar(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<TvShowsResponse>
}