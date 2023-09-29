package com.androidhacks.merakiliteai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidhacks.merakiliteai.models.CourseContainer
import com.androidhacks.merakiliteai.models.CourseExerciseContainer
import com.androidhacks.merakiliteai.models.Pathway
import com.androidhacks.merakiliteai.models.PathwayContainer
import com.androidhacks.merakiliteai.repository.HomeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel (private val repo: HomeRepo) : ViewModel() {
    init {

        viewModelScope.launch(Dispatchers.IO) {
            repo.getPathways()
            repo.getCourses()
            repo.getCoursesExercise()
        }
    }

    fun getCourse(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getCourses()
        }
    }

    fun getCourseExercise(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getCoursesExercise()
        }
    }
    val pathwayContainer : LiveData<PathwayContainer> = repo.pathways

    val courseContainer : LiveData<CourseContainer> = repo.courses

    val courseContainerExercise : LiveData<CourseExerciseContainer> = repo.courseExerciseContainer

}