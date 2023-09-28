package com.androidhacks.merakiliteai.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidhacks.merakiliteai.models.PathwayContainer
import com.androidhacks.merakiliteai.network.ApiServices


class HomeRepo(private val apiServices: ApiServices, private val context: Context) {

    private val pathwayList = MutableLiveData<PathwayContainer>()

    val pathways : LiveData<PathwayContainer> = pathwayList



    suspend fun getPathways() {
        try {
            val pathways = apiServices.getPathways()
            pathwayList.postValue(pathways)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}