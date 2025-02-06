package com.kmp.idea.data.service.firebase

import com.dardev.land.domain.repository.IFirebasePostService
import com.kmp.idea.domain.model.Post
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class FirebasePostService:IFirebasePostService {
    private val db = Firebase.firestore

    override suspend fun getAllPost(): List<LandPost> {
        val postCollection = db.collection("posts").get()
        val allPostList = ArrayList<LandPost>()

        if(postCollection != null){
            postCollection.documents.forEach {
                val documentPost = db.collection("posts").document(it.id).get() // bug ici
                val post = LandPost(
                    PostId = it.id,
                    UserId = documentPost.get("UserId"),
                    Title = documentPost.get("Title"),
                    Description = documentPost.get("Description"),
                    Picture = documentPost.get("Picture"),
                    Latitude = documentPost.get("Latitude"),
                    Longitude = documentPost.get("Longitude"),
                    IsPrivate = documentPost.get("IsPrivate")
                )
                allPostList.add(post)
            }
        }
        return allPostList
    }

    override suspend fun getPostById(postId:String): LandPost {
        val documentPost = db.collection("posts").document(postId).get()
        val postInfo = LandPost(
            PostId = postId,
            UserId = documentPost.get("UserId"),
            Title = documentPost.get("Title"),
            Description = documentPost.get("Description"),
            Picture = documentPost.get("Picture"),
            Latitude = documentPost.get("Latitude"),
            Longitude = documentPost.get("Longitude"),
            IsPrivate = documentPost.get("IsPrivate")
        )
        return postInfo
    }

    override suspend fun userLikeAPost(postId: String, userId: String) {
        val documentPost = db.collection("posts").document(postId).get()
        var countLikes : Int = documentPost.get("Likes")// Faire + 1
        val dataLike = hashMapOf(
            "Likes" to countLikes + 1,
            // "UserId" to userId
        )
        db.collection("posts").document(postId).update(dataLike)
    }

    override suspend fun userDislikeAPost(postId: String, userId: String) {
        val documentPost = db.collection("posts").document(postId).get()
        var countLikes : Int = documentPost.get("Likes")// Faire - 1
        val dataLike = hashMapOf(
            "Likes" to countLikes - 1,
            // "UserId" to userId
        )
        db.collection("posts").document(postId).update(dataLike)
    }

    override suspend fun saveNewPost(landPost: LandPost) {
        // Ajout var isPrivate
        val post = hashMapOf(
            "Description" to landPost.Description,
            "Latitude" to landPost.Latitude,
            "Likes" to 0,
            "Longitude" to landPost.Longitude,
            "Picture" to landPost.Picture,
            "Title" to landPost.Title,
            "UserId" to landPost.UserId,
            "IsPrivate" to landPost.IsPrivate
        )
        db.collection("posts").add(post).collection("comment")
    }

}
