package com.androidhacks.merakiliteai

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PathwayAPI {

    @GET("pathways")
    suspend fun getPathways(
    ): PathwayContainer

    @GET("courses/{course_id}/exercises")
    suspend fun getCourseContentAsync(
        @Path("course_id") course_id: String,
        @Query("lang") language: String
    ): CourseExerciseContainer


}