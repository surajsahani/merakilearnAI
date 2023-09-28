package com.androidhacks.merakiliteai

data class Pathway(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    var courses: List<Course> = arrayListOf()
)