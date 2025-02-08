package com.kmp.idea.android.cache.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kmp.idea.android.cache.ISessionCache
import com.kmp.idea.android.cache.session.Session
import com.kmp.idea.domain.model.TokenConnexion

class RefreshSessionWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val sessionCache: ISessionCache,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val session = sessionCache.getActiveSession()
        return if (session != null) {
            refreshSession(session)
            Result.success()
        } else {
            Result.failure()
        }
    }

    private suspend fun refreshSession(session: Session) {
        val token = session.dataSession.jwtToken
        val userId = session.dataSession.currentUserGuid

        val sessionRefresh = "test"
        val newSession =
            sessionRefresh?.let {
                Session(
                    "",
                    TokenConnexion("", ""),
                )
            }

        if (newSession != null) {
            sessionCache.saveSession(newSession)
        }
    }
}
