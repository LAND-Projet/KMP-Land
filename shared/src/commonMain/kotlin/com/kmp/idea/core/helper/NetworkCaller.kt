package com.kmp.idea.core.helper

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse

class NetworkCallerImpl(private val client: HttpClient) : NetworkCaller {

    override suspend fun <T> executeSafeCall(
        client: HttpClient,
        nameEvent: String,
        requestBuilder: HttpRequestBuilder.() -> Unit,
        transformResponse: suspend (HttpResponse) -> T?
    ): T? {
        try {
            val response = client.request(
                // Configure the HTTP request using the requestBuilder
                requestBuilder
            )

            // Transform and return the response
            return transformResponse(response)
        } catch (e: Exception) {
            // Handle any exceptions here
            // You can log errors or perform error handling as needed
            return null
        }
    }
}