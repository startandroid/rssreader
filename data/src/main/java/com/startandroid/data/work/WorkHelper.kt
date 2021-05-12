package com.startandroid.data.work

import androidx.work.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkHelper @Inject constructor(
    private val workManager: WorkManager
) {

    fun scheduleRefreshItems() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<RefreshItemsWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
                "refreshItems",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
    }

}