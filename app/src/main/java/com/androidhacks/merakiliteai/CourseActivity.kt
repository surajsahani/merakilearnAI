package com.androidhacks.merakiliteai

import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidhacks.merakiliteai.adapter.CourseAdapter
import com.androidhacks.merakiliteai.adapter.PathwayAdapter
import com.androidhacks.merakiliteai.databinding.ActivityCourseBinding
import com.androidhacks.merakiliteai.models.Course
import com.androidhacks.merakiliteai.viewmodel.HomeViewModel
import com.androidhacks.merakiliteai.viewmodel.HomeViewModelFactory

class CourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_course)

        val repository = (application as AppApplication).repo
        viewModel = ViewModelProvider(this, HomeViewModelFactory(repository)).get(HomeViewModel::class.java)


        val selectedPathwayId = intent.getIntExtra("selectedPathwayIndex", 0)


        viewModel.courseContainer.observe(this) {
            Log.d("CourseActivity", "onCreate: ${it}")
            initCourseRecyclerView(it)

        }
    }

    private fun initCourseRecyclerView(courses : List<Course>){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.courseRecycler.layoutManager = layoutManager

        val adapter = CourseAdapter(courses) {
            viewModel.getCourseExercise()
            Toast.makeText(this, "CLicked on ${it.name}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        binding.courseRecycler.adapter = adapter
    }
}