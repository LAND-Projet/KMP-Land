package com.kmp.idea.data.service

import com.kmp.idea.domain.model.Post
import com.kmp.idea.domain.remote.IPostAPI
import com.kmp.idea.domain.use_cases.ValidateResult

class PostAPIService(
    private val postAPI: IPostAPI
) {
    suspend fun createNewPost(post: Post, jwtToken: String): ValidateResult? {
        return postAPI.postNewPost(post, jwtToken)
    }

}