package com.androidhacks.merakiliteai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidhacks.merakiliteai.databinding.ActivityCourseBinding
import com.androidhacks.merakiliteai.databinding.ActivityExerciseBinding
import com.androidhacks.merakiliteai.models.Course
import com.androidhacks.merakiliteai.viewmodel.HomeViewModel
import com.androidhacks.merakiliteai.viewmodel.HomeViewModelFactory

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise)

        val selectedId = intent.getIntExtra("selectedPathwayIndex", 0)

        val repository = (application as AppApplication).repo
        viewModel = ViewModelProvider(this, HomeViewModelFactory(repository)).get(HomeViewModel::class.java)

        viewModel.courseContainerExercise.observe(this) {
            Log.d("ExerciseActivity", "onCreate: ${it}")
            //initCourseRecyclerView(it)
            viewModel.getCourseExercise()



        }

    }

    private fun initCourseRecyclerView(courses : List<Course>){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.courseRecyclerExercise.layoutManager = layoutManager

        val adapter = ExerciseAdapter(courses) {
            viewModel.getCourseExercise()
            Toast.makeText(this, "CLicked on ${it.name}", Toast.LENGTH_SHORT).show()
        }

        binding.courseRecyclerExercise.adapter = adapter
    }
}