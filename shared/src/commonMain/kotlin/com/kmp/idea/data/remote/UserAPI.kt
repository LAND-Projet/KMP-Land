package com.kmp.idea.data.remote

import com.kmp.idea.core.helper.NetworkCaller
import com.kmp.idea.core.util.Constants
import com.kmp.idea.domain.model.User
import com.kmp.idea.domain.remote.IUserAPI
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

class UserAPI(private val networkCaller: NetworkCaller): IUserAPI {
    private val client = HttpClient()

    override suspend fun getUserById(userGuid: String,jwtToken: String): User? {
        return networkCaller.executeSafeCall(client, "GET User By Id",
            {
                url(Constants.sessionAPIUrl + "user/id/$userGuid")
                method = HttpMethod.Get
                header(HttpHeaders.Authorization, "Bearer $jwtToken")
            },
            { response ->
                if (response.status.isSuccess()) {
                    withContext(Dispatchers.IO) {
                        val responseBody = response.body<String>()
                        val json: JsonElement = Json.parseToJsonElement(responseBody)

                        val userJsonObject = json.jsonObject

                        val userGuid = userJsonObject["UserUUID"]?.jsonPrimitive?.content
                        val email = userJsonObject["Email"]?.jsonPrimitive?.content
                        val profilPicture = userJsonObject["Picture"]?.jsonPrimitive?.content

                        if (userGuid != null && email != null && profilPicture != null) {
                            User(
                                userGuid,
                                email,
                                profilPicture,
                            )
                        } else {
                            null
                        }
                    }
                } else {
                    null
                }
            }
        )
    }
}