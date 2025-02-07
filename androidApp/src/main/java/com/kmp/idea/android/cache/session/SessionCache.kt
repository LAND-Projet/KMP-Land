package com.kmp.idea.android.cache.session

import android.content.SharedPreferences
import com.kmp.idea.android.cache.ISessionCache
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class SessionCache(
    private val sharedPreferences: SharedPreferences
) : ISessionCache {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val adapter = moshi.adapter(Session::class.java)
    override fun saveSession(session: Session) {
        sharedPreferences.edit()
            .putString("session", adapter.toJson(session))
            .apply()
    }

    override fun getActiveSession(): Session? {
        val json = sharedPreferences.getString("session", null) ?: return null
        return adapter.fromJson(json)
    }

    override fun clearSession() {
        sharedPreferences.edit().remove("session").apply()
    }

}