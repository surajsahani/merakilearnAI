package com.androidhacks.merakiliteai.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidhacks.merakiliteai.databinding.ItemExerciseBinding
import com.androidhacks.merakiliteai.models.Course

class ExerciseAdapter(private val courses : List<Course>, private val onCourseClick : (Course) -> Unit) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(courses[position])
    }


    override fun getItemCount(): Int {
        return courses.size
    }

    inner class ExerciseViewHolder(private val binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) {
            binding.exercise.text = course.name
        }
    }
}