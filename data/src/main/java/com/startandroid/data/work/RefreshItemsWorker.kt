package com.startandroid.data.work

import android.content.Context
import android.util.Log
//import androidx.hilt.work.HiltWorker
//import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.startandroid.domain.usecase.RefreshItemsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

//@HiltWorker
class RefreshItemsWorker
//@AssistedInject
constructor(
    context: Context,
    params: WorkerParameters,
    private val refreshItemsUseCase: RefreshItemsUseCase
): CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        refreshItemsUseCase.invoke()
        return Result.success()
    }

}