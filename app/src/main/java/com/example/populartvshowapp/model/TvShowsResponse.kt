package com.example.populartvshowapp.model

data class TvShowsResponse(
    val page: Int?,
    val results: List<SimilarResponse>?,
    val total_pages: Int?,
    val total_results: Int?
)