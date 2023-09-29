package com.androidhacks.merakiliteai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidhacks.merakiliteai.adapter.PathwayAdapter
import com.androidhacks.merakiliteai.databinding.ActivityMainBinding
import com.androidhacks.merakiliteai.models.Pathway
import com.androidhacks.merakiliteai.viewmodel.HomeViewModel
import com.androidhacks.merakiliteai.viewmodel.HomeViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        binding.searchBoxContainer.root.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
            }
            startActivityForResult(intent, SPEECH_REQUEST_CODE)
        }

        val repository = (application as AppApplication).repo
        viewModel = ViewModelProvider(this, HomeViewModelFactory(repository)).get(HomeViewModel::class.java)

        viewModel.pathwayContainer.observe(this) {
            initPathwayRecyclerView(it.pathways)
            Log.d("MainActivity", "onCreate: ${it.pathways}")
        }


    }

    private fun initPathwayRecyclerView(pathway: List<Pathway>) {
        val layoutManager = LinearLayoutManager(this)
        binding.pathwayRecycler.layoutManager = layoutManager

        val adapter = PathwayAdapter(pathway) {
            viewModel.getCourse()
            viewModel.getCourseExercise()
            Toast.makeText(this, "CLicked on ${it.name}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CourseActivity::class.java)
            intent.putExtra("selectedPathwayIndex", it.id)
            startActivity(intent)
        }

        binding.pathwayRecycler.adapter = adapter
    }

    companion object {
        const val SPEECH_REQUEST_CODE = 0
    }

}