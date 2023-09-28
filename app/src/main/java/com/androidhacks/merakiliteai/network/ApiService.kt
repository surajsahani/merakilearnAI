package com.androidhacks.merakiliteai.network

import com.androidhacks.merakiliteai.models.CourseContainer
import com.androidhacks.merakiliteai.models.PathwayContainer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("pathways")
    suspend fun getPathways(
        @Query("appVersion") appVersion: Int = 65,
        @Query("courseType") coursetype: String = "json",
    ): PathwayContainer


    @GET("pathways/{pathwayId}/courses")
    suspend fun getCourseById(
        @Path("pathwayId") pathwayId: Int,
        @Query("appVersion") appVersion: Int = 65,
    ) : CourseContainer
}