package com.androidhacks.merakiliteai.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidhacks.merakiliteai.local.AppDatabase
import com.androidhacks.merakiliteai.local.PathwayEntity
import com.androidhacks.merakiliteai.models.CourseContainer
import com.androidhacks.merakiliteai.models.CourseExerciseContainer
import com.androidhacks.merakiliteai.models.PathwayContainer
import com.androidhacks.merakiliteai.network.ApiServices
import com.androidhacks.merakiliteai.utils


class HomeRepo(
    private val apiServices: ApiServices,
    private val context: Context,
    private val database: AppDatabase,
) {

    private val pathwayList = MutableLiveData<PathwayContainer>()
    private val courseList = MutableLiveData<CourseContainer>()
    private val courseContentExercise = MutableLiveData<CourseExerciseContainer>()

    val pathways : LiveData<PathwayContainer> = pathwayList
    val courses : LiveData<CourseContainer> = courseList
    val courseExerciseContainer : LiveData<CourseExerciseContainer> = courseContentExercise


    suspend fun getPathways() {
        try {
            if (utils.isInternetAvailable(context)==true) {
                val pathways = apiServices.getPathways()
                database.pathwayDao().insertPathway(pathways.pathways)
                pathwayList.postValue(pathways)
            } else {
                pathwayList.postValue(PathwayContainer(getPathwaysFromDb()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    suspend fun getPathwaysFromDb() : List<PathwayEntity> {
        return database.pathwayDao().getAllPathways()
    suspend fun getCourses(){
        try {
            val courses = apiServices.getCourseById(1,"json")
            courseList.postValue(courses)
        } catch (e: Exception) {
            Log.d("HomeRepo", "getCourses: ${e.message}")
        }
    }

    suspend fun getCoursesExercise(){
        try {
            val coursesExercise = apiServices.getCourseContentAsync(87,"en")
            courseContentExercise.postValue(coursesExercise)
        } catch (e: Exception) {
            Log.d("HomeRepo", "getCourses: ${e.message}")
        }
    }

}