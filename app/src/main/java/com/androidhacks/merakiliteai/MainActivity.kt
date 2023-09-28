package com.androidhacks.merakiliteai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.androidhacks.merakiliteai.databinding.ActivityMainBinding
import com.androidhacks.merakiliteai.viewmodel.HomeViewModel
import com.androidhacks.merakiliteai.viewmodel.HomeViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        val repository = (application as AppApplication).repo
        viewModel = ViewModelProvider(this, HomeViewModelFactory(repository)).get(HomeViewModel::class.java)

        viewModel.pathwayContainer.observe(this) {
//            initPathwayRecyclerView(it.pathways)
            Log.d("MainActivity", "onCreate: ${it.pathways}")
        }


    }

}