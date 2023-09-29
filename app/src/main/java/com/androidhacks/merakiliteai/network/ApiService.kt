package com.androidhacks.merakiliteai.network

import com.androidhacks.merakiliteai.models.Course
import com.androidhacks.merakiliteai.models.CourseContainer
import com.androidhacks.merakiliteai.models.CourseExerciseContainer
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


    @GET("pathways/{pathway_id}/courses")
    suspend fun getCourseById(
        @Path("pathway_id") pathway_id: Int,
        @Query("courseType") coursetype: String = "json",
    ) : CourseContainer

    @GET("courses/{course_id}/exercises")
    suspend fun getCourseContentAsync(
        @Path("course_id") course_id: Int,
        @Query("lang") language: String
    ): CourseExerciseContainer
}