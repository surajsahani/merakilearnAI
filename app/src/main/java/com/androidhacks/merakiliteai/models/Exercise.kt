package com.androidhacks.merakiliteai.models

data class Exercise(
    val content: List<Content>,
    val course_id: String,
    val github_link: String,
    val id: String,
    val name: String,
    val parent_exercise_id: String,
    val review_type: String,
    val sequence_num: Int,
    val slug: String,
    val solution: Any,
    val submission_type: Any
)