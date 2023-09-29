package com.androidhacks.merakiliteai

import android.app.Application
import com.androidhacks.merakiliteai.local.AppDatabase
import com.androidhacks.merakiliteai.network.ApiServices
import com.androidhacks.merakiliteai.network.RetrofitInstance
import com.androidhacks.merakiliteai.repository.HomeRepo

class AppApplication : Application() {

    lateinit var repo: HomeRepo

    override fun onCreate() {
        super.onCreate()

        val apiService = RetrofitInstance.getInstance().create(ApiServices::class.java)
        val database = AppDatabase.getDatabase(applicationContext)

        repo = HomeRepo(apiService, applicationContext, database)
    }
}