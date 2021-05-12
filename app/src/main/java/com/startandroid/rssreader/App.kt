package com.startandroid.rssreader

import android.app.Application
import androidx.work.*
import com.startandroid.data.work.WorkHelper
import com.startandroid.data.work.WorkManagerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App: Application(), Configuration.Provider {

    @Inject lateinit var workerFactory: WorkManagerFactory

    @Inject lateinit var workHelper: WorkHelper

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        workHelper.scheduleRefreshItems()
    }

}