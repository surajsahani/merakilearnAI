package com.androidhacks.merakiliteai.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidhacks.merakiliteai.local.AppDatabase
import com.androidhacks.merakiliteai.local.PathwayEntity
import com.androidhacks.merakiliteai.models.PathwayContainer
import com.androidhacks.merakiliteai.network.ApiServices
import com.androidhacks.merakiliteai.utils


class HomeRepo(
    private val apiServices: ApiServices,
    private val context: Context,
    private val database: AppDatabase,
) {

    private val pathwayList = MutableLiveData<PathwayContainer>()

    val pathways : LiveData<PathwayContainer> = pathwayList



    suspend fun getPathways() {
        try {
            if (utils.isInternetAvailable(context)==true) {
                val pathways = apiServices.getPathways()
                database.pathwayDao().insertPathway(pathways.pathways)
                pathwayList.postValue(pathways)
            } else {
                getPathwaysFromDb()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    suspend fun getPathwaysFromDb() : List<PathwayEntity> {
        return database.pathwayDao().getAllPathways()
    }

}