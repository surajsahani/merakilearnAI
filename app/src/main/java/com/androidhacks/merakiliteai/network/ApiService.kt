package com.androidhacks.merakiliteai.network

import com.androidhacks.merakiliteai.BuildConfig
import com.androidhacks.merakiliteai.models.PathwayContainer
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("pathways")
    suspend fun getPathways(
        @Query("appVersion") appVersion: Int = BuildConfig.VERSION_CODE,
        @Query("courseType") coursetype: String = "json",
    ): PathwayContainer
}