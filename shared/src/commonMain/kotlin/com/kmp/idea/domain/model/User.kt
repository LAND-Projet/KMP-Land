package com.kmp.idea.domain.model

import kotlinx.serialization.Serializable

// add isprivate value
@Serializable
data class User(
    val UserId: String,
    val Email: String,
    val ProfilPicture: String
)