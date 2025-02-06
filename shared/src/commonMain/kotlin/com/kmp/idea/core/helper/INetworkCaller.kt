package com.kmp.idea.core.helper

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

interface NetworkCaller {
    suspend fun <T> executeSafeCall(
        client: HttpClient,
        nameEvent: String,
        requestBuilder: HttpRequestBuilder.() -> Unit,
        transformResponse: suspend (HttpResponse) -> T?
    ): T?
}