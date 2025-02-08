package com.kmp.idea.android.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.kmp.idea.android.cache.ISessionCache
import com.kmp.idea.android.cache.worker.RefreshSessionWorker
import org.koin.androidx.compose.inject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinWorkerFactory : WorkerFactory(), KoinComponent {
    private val sessionCache: ISessionCache by inject()

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return when (workerClassName) {
            RefreshSessionWorker::class.java.name -> {
                RefreshSessionWorker(appContext, workerParameters, sessionCache)
            }
            else -> null
        }
    }
}
