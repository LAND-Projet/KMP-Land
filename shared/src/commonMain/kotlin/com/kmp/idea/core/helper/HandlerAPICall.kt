package com.kmp.idea.core.helper

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readBytes
import io.ktor.client.statement.readText
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

suspend inline fun <reified T> executeSafeCall(
    client: HttpClient,
    nameEvent: String,
    crossinline requestBuilder: HttpRequestBuilder.() -> Unit,
    crossinline transformResponse: suspend (HttpResponse) -> T?
): T? {
    val viewScope = CoroutineScope(Dispatchers.IO + CoroutineName(nameEvent))
    var result: T? = null
    try {
        viewScope.launch {
            try {
                val response: HttpResponse = client.request {
                    requestBuilder() // Build the request using the provided builder
                }
                result = transformResponse(response)
            } catch (e: RedirectResponseException) {
                Throwable("Error: ${e.response.status.description}")
            } catch (e: ClientRequestException) {
                Throwable("Error: ${e.response.status.description}")
            } catch (e: ServerResponseException) {
                Throwable("Error: ${e.response.status.description}")
            } catch (e: Exception) {
                Throwable("Error: ${e.message}")
            }
        }.join()
    } catch (e: Exception) {
        Throwable("Error during launch: ${e.message}")
    }
    return result
}
