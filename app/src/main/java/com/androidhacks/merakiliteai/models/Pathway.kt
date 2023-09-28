package com.androidhacks.merakiliteai.models

data class Pathway(
    val code: String,
    val courses: List<Course>,
    val createdAt: String,
    val cta: Any,
    val description: String,
    val id: Int,
    val logo: String,
    val name: String,
    val platform: String,
    val shouldShowCertificate: Boolean,
    val sub_description: String,
    val video_link: String
)