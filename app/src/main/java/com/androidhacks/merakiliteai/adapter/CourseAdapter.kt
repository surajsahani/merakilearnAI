package com.androidhacks.merakiliteai.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidhacks.merakiliteai.databinding.ItemCourseBinding
import com.androidhacks.merakiliteai.models.Course
import com.bumptech.glide.Glide

class CourseAdapter(private val courses : List<Course> , private val onCourseClick : (Course) -> Unit) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    inner class CourseViewHolder(private val binding: ItemCourseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) {
            binding.courseName.text = course.name
            binding.descriptionTxt.text = course.short_description
            Glide.with(binding.courseLogo.context)
                .load(course.logo)
                .into(binding.courseLogo)


            binding.root.setOnClickListener {
                onCourseClick(course)
            }
        }

    }
}