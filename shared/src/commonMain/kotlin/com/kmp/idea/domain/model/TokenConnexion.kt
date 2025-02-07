package com.kmp.idea.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenConnexion(
    val currentUserGuid:String,
    val jwtToken:String
)
