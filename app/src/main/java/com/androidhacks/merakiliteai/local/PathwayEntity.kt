package com.androidhacks.merakiliteai.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.androidhacks.merakiliteai.converter.CourseListConverter
import com.androidhacks.merakiliteai.models.Course

@Entity(tableName = "pathways")
data class PathwayEntity(

    val code:String,

    @TypeConverters(CourseListConverter::class)

    var courses: List<Course> = arrayListOf(),

    val description: String,

    @PrimaryKey val id: Int,

    val name: String,

    val logo:String
)
