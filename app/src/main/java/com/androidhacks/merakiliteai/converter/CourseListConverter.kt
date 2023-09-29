package com.androidhacks.merakiliteai.converter

import androidx.room.TypeConverter
import com.androidhacks.merakiliteai.models.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CourseListConverter {

    @TypeConverter
    fun fromCourseList(courseList: List<Course>): String {
        val gson = Gson()
        return gson.toJson(courseList)
    }
    @TypeConverter
    fun toCourseList(courseListString: String): List<Course> {
        val gson = Gson()
        val type = object : TypeToken<List<Course>>() {}.type
        return gson.fromJson(courseListString, type)
    }

}

