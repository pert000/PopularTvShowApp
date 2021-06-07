package com.example.populartvshowapp.model

data class SimilarTvShowsModel(
    val page: Int?,
    val results: List<ResultX>?,
    val total_pages: Int?,
    val total_results: Int?
)