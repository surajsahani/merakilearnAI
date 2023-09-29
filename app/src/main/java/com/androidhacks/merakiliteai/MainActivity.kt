package com.androidhacks.merakiliteai

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidhacks.merakiliteai.adapter.PathwayAdapter
import com.androidhacks.merakiliteai.databinding.ActivityMainBinding
import com.androidhacks.merakiliteai.local.PathwayEntity
import com.androidhacks.merakiliteai.models.CourseData
import com.androidhacks.merakiliteai.viewmodel.HomeViewModel
import com.androidhacks.merakiliteai.viewmodel.HomeViewModelFactory
import java.util.Locale

class MainActivity : AppCompatActivity(), CourseAdapter.CourseAdapterListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var sportsList: List<CourseData>
    private lateinit var voiceSearchImageView: ImageView
    private lateinit var clearQueryImageView: ImageView
    private lateinit var noSearchResultsFoundText: TextView
    private lateinit var editText: AppCompatEditText
    private lateinit var adapterPath:CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        voiceSearchImageView = findViewById(R.id.voice_search_query)
        clearQueryImageView = findViewById(R.id.clear_search_query)
        noSearchResultsFoundText = findViewById(R.id.no_search_results_found_text)
        editText = findViewById(R.id.search_edit_text)

        voiceSearchImageView = findViewById(R.id.voice_search_query)
        clearQueryImageView = findViewById(R.id.clear_search_query)
        noSearchResultsFoundText = findViewById(R.id.no_search_results_found_text)
        editText = findViewById(R.id.search_edit_text)
        binding.searchBoxContainer.root.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
            }
            startActivityForResult(intent, SPEECH_REQUEST_CODE)
        }
        voiceSearchImageView.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
            }
            startActivityForResult(intent, SPEECH_REQUEST_CODE)
        }

        clearQueryImageView.setOnClickListener {
            editText.setText("")
        }

        sportsList = sportsList(applicationContext)
        attachAdapter(sportsList)


        editText.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().toLowerCase(Locale.getDefault())
            filterWithQuery(query)
            toggleImageView(query)
        }


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

    fun initPathwayRecyclerView(pathway: List<PathwayEntity>) {
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
    private fun attachAdapter(list: List<CourseData>) {
        adapterPath = CourseAdapter(list, this)
        binding.pathwayRecycler.adapter = adapterPath
    }
    private fun toggleImageView(query: String) {
        if (query.isNotEmpty()) {
            clearQueryImageView.visibility = View.VISIBLE
            voiceSearchImageView.visibility = View.INVISIBLE
        } else if (query.isEmpty()) {
            clearQueryImageView.visibility = View.INVISIBLE
            voiceSearchImageView.visibility = View.VISIBLE
        }
    }

    private fun toggleRecyclerView(sportsList: List<CourseData>) {
        if (sportsList.isEmpty()) {
            binding.pathwayRecycler.visibility = View.INVISIBLE
            noSearchResultsFoundText.visibility = View.VISIBLE
        } else {
            binding.pathwayRecycler.visibility = View.VISIBLE
            noSearchResultsFoundText.visibility = View.INVISIBLE
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val spokenText: String? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results?.get(0)
                }
            // Do something with spokenText
            editText.setText(spokenText)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun filterWithQuery(query: String) {
        if (query.isNotEmpty()) {
            val filteredList: List<CourseData> = onFilterChanged(query)
            attachAdapter(filteredList)
            toggleRecyclerView(filteredList)
        } else if (query.isEmpty()) {
            attachAdapter(sportsList)
        }
    }

    private fun onFilterChanged(filterQuery: String): List<CourseData> {
        val filteredList = ArrayList<CourseData>()
        for (currentSport in sportsList) {
            if (currentSport.title.toLowerCase(Locale.getDefault()).contains(filterQuery)) {
                filteredList.add(currentSport)
            }
        }
        return filteredList
    }

    override fun onSportSelected(sports: CourseData?) {

    }

}