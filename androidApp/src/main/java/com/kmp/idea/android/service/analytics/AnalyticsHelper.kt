package com.kmp.idea.android.service.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class AnalyticsHelper(private val context: Context) {
    private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    fun logEvent(
        eventName: String,
        params: Bundle? = null,
    ) {
        firebaseAnalytics.logEvent(eventName, params)
    }

    fun setUserId(userId: String) {
        firebaseAnalytics.setUserId(userId)
    }

    fun setUserProperty(
        value: String?,
        property: String,
    ) {
        firebaseAnalytics.setUserProperty(property, value)
    }
}
