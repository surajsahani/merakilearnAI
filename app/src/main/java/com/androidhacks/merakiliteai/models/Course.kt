package com.androidhacks.merakiliteai.models

data class Course(
    val completed_portion: Int,
    val id: Int,
    val lang_available: List<String>,
    val logo: String,
    val name: String,
    val short_description: String
)
