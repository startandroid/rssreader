package com.startandroid.data.work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.startandroid.domain.repository.FeedRepository
import com.startandroid.domain.usecase.RefreshItemsUseCase
import javax.inject.Inject

class WorkManagerFactory @Inject constructor(
    private val refreshItemsUseCase: RefreshItemsUseCase,
    private val feedRepository: FeedRepository,
): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return when (workerClassName) {
            RefreshItemsWorker::class.java.name -> RefreshItemsWorker(appContext, workerParameters, refreshItemsUseCase)
            else -> null
        }

    }

}