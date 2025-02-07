package com.kmp.idea.data.remote

import com.kmp.idea.core.helper.NetworkCaller
import com.kmp.idea.core.util.Constants
import com.kmp.idea.domain.model.Post
import com.kmp.idea.domain.remote.IAuthAPI
import com.kmp.idea.domain.remote.IPostAPI
import com.kmp.idea.domain.use_cases.ValidateResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.isSuccess
import io.ktor.util.InternalAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class PostAPI(private val networkCaller: NetworkCaller): IPostAPI {
    private val client = HttpClient()

    @OptIn(InternalAPI::class)
    override suspend fun postNewPost(post: Post, jwtToken: String): ValidateResult? {
        val jsonBody = """
            {
                "PostGUID": "v",
                "UserGUID": "${post.UserId}",
                "Picture": "${post.Picture}",
                "Description": "${post.Description}",
            }
        """.trimIndent()

        return networkCaller.executeSafeCall(client, "POST publish a Post",
            {
                url(Constants.sessionAPIUrl + "post/publish")
                method = HttpMethod.Post
                body = TextContent(jsonBody, ContentType.Application.Json)
                header(HttpHeaders.Authorization, "Bearer $jwtToken")
            },
            { response ->
                if (response.status.isSuccess()) {
                    ValidateResult(
                        successful = true
                    )
                } else {
                    ValidateResult(
                        successful = false,
                        errorString = "Error: ${response.status.description}"
                    )
                }
            }
        )
    }
}