package com.kmp.idea.data.remote

import com.kmp.idea.core.helper.NetworkCaller
import com.kmp.idea.core.util.Constants
import com.kmp.idea.domain.model.SignInData
import com.kmp.idea.domain.model.SignUpData
import com.kmp.idea.domain.model.TokenConnexion
import com.kmp.idea.domain.remote.IAuthAPI
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

class AuthAPI(private val networkCaller: NetworkCaller):IAuthAPI {
    private val client = HttpClient()

    @OptIn(InternalAPI::class)
    override suspend fun signUpToApp(signUpData: SignUpData): TokenConnexion? {
        val jsonBody = """
            {
                "UserUUID": "0",
                "Email": "${signUpData.email}",
                "Hash": "1",
                "Picture": " ",
            }
        """.trimIndent()

        return networkCaller.executeSafeCall(client,"POST User Create Account",
            {
                url(Constants.authAPIUrl + "user/register/account/${signUpData.password}")
                method = HttpMethod.Post
                body = TextContent(jsonBody, ContentType.Application.Json)
            },{ response ->
                if (response.status.isSuccess()) {
                    withContext(Dispatchers.IO) {
                        val responseBody = response.body<String>()
                        val json: JsonElement = Json.parseToJsonElement(responseBody)
                        val connexionJson = json.jsonObject
                        when {
                            true -> {
                                val currentId = connexionJson.getValue("user")?.jsonPrimitive?.content
                                val token = connexionJson.getValue("token")?.jsonPrimitive?.content
                                if(currentId != null && token != null){
                                    TokenConnexion(
                                        currentUserGuid = currentId,
                                        jwtToken = token
                                    )
                                } else {
                                    null
                                }
                            }
                            else -> null
                        }
                    }
                } else {
                    null
                }
            }
        )
    }

    override suspend fun signInToApp(signInData: SignInData): TokenConnexion? {
        return networkCaller.executeSafeCall(client,"POST User Connect to App",
            {
                url(Constants.authAPIUrl + "user/login/app/${signInData.email}/${signInData.password}")
                method = HttpMethod.Post
            },{ response ->
                if (response.status.isSuccess()) {
                    withContext(Dispatchers.IO) {
                        val responseBody = response.body<String>()
                        val json: JsonElement = Json.parseToJsonElement(responseBody)
                        val connexionJson = json.jsonObject
                        when {
                            true -> {
                                val currentId = connexionJson.getValue("user")?.jsonPrimitive?.content
                                val token = connexionJson.getValue("token")?.jsonPrimitive?.content
                                if(currentId != null && token != null){
                                    TokenConnexion(
                                        currentUserGuid = currentId,
                                        jwtToken = token
                                    )
                                } else {
                                    null
                                }
                            }
                            else -> null
                        }
                    }
                } else {
                    null
                }
            }
        )
    }

    @OptIn(InternalAPI::class)
    override suspend fun refreshJWTToken(token: String,userId: String): TokenConnexion? {
        val jsonBody = """
            {
                "token": "${token}"
            }
        """.trimIndent()

        return networkCaller.executeSafeCall(client,"POST Refresh JWT Token",
            {
                url(Constants.sessionAPIUrl + "user/refresh/jwt/token")
                method = HttpMethod.Post
                body = TextContent(jsonBody, ContentType.Application.Json)
                header(HttpHeaders.Authorization, "Bearer $token")
            },{ response ->
                if (response.status.isSuccess()) {
                    withContext(Dispatchers.IO) {
                        val responseBody = response.body<String>()
                        val json: JsonElement = Json.parseToJsonElement(responseBody)
                        val connexionJson = json.jsonObject
                        when {
                            true -> {
                                val token = connexionJson.getValue("token")?.jsonPrimitive?.content
                                if(token != null){
                                    TokenConnexion(
                                        currentUserGuid = userId,
                                        jwtToken = token
                                    )
                                } else {
                                    null
                                }
                            }
                            else -> null
                        }
                    }
                } else {
                    null
                }
            }
        )
    }
}