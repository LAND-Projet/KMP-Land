package com.kmp.idea.domain.remote

import com.kmp.idea.domain.model.Post

interface IFirebasePostService {
    suspend fun getAllPost(): List<Post>
    suspend fun getPostById(postId:String): Post
    suspend fun userLikeAPost(postId: String,userId: String)
    suspend fun userDislikeAPost(postId: String,userId: String)
    suspend fun saveNewPost(Post: Post)
}