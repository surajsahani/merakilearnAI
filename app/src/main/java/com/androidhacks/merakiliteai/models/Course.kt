package com.androidhacks.merakiliteai.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Course(
    @PrimaryKey  val id: Int,
    val logo: String,
    val name: String,
    val short_description: String,
    val completed_portion: Int,
)
