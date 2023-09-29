package com.androidhacks.merakiliteai.models

import androidx.room.Ignore

data class Course(
    val id: Int,
    val logo: String,
    val name: String,
    val short_description: String,
    val completed_portion: Int,
    val exercises : List<Exercise>
)
