package com.androidhacks.merakiliteai.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidhacks.merakiliteai.models.Course


@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: List<Course>?)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCourse(course: Course)
//
//    @Query("SELECT * FROM courses")
//    suspend fun getAllCourses(): List<Course>?

    @Query("SELECT * FROM courses WHERE id = :id")
    suspend fun getCourseById(id:String): List<Course>?


}