package com.startandroid.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface FeedServiceApi {

    @GET
    suspend fun fetchFeed(@Url url: String): FeedApi

}