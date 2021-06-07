package com.example.populartvshowapp.di

import com.example.populartvshowapp.date.ApiService
import com.example.populartvshowapp.date.DateSource
import com.example.populartvshowapp.date.SimilarPagingDataSource
import com.example.populartvshowapp.date.TvShowsPagingDataSource
import com.example.populartvshowapp.repository.DetailsRepository
import com.example.populartvshowapp.repository.SimilarTvShowsRepository
import com.example.populartvshowapp.repository.TvShowsRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        )
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()


    @Provides
    fun commonService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideDataSource(apiService: ApiService) = TvShowsPagingDataSource(apiService)

    @Singleton
    @Provides
    fun repository(dataSource: TvShowsPagingDataSource) =
        TvShowsRepository(dataSource)

    @Singleton
    @Provides
    fun provideDetailsDataSource(apiService: ApiService) = DateSource(apiService)

    @Singleton
    @Provides
    fun repositoryDetails(dataSource: DateSource) =
        DetailsRepository(dataSource)

    @Singleton
    @Provides
    fun repositorySimilarTvShows(dataSource: SimilarPagingDataSource) =
        SimilarTvShowsRepository(dataSource)

}