package com.kmp.idea.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val PostId:String,
    val UserId:String,
    val Description:String,
    val Picture:String
)
