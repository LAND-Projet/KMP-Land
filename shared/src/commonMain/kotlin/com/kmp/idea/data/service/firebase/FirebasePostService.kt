package com.kmp.idea.data.service.firebase

import com.kmp.idea.domain.repository.IFirebasePostService
import com.kmp.idea.domain.model.Post
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class FirebasePostService:IFirebasePostService {
    private val db = Firebase.firestore

    override suspend fun getAllPost(): List<Post> {
        val postCollection = db.collection("posts").get()
        val allPostList = ArrayList<Post>()

        if(postCollection != null){
            postCollection.documents.forEach {
                val documentPost = db.collection("posts").document(it.id).get() // bug ici
                val post = Post(
                    PostId = it.id,
                    UserId = documentPost.get("UserId"),
                    Description = documentPost.get("Description"),
                    Picture = documentPost.get("Picture"),
                )
                allPostList.add(post)
            }
        }
        return allPostList
    }

    override suspend fun getPostById(postId:String): Post {
        val documentPost = db.collection("posts").document(postId).get()
        val postInfo = Post(
            PostId = postId,
            UserId = documentPost.get("UserId"),
            Description = documentPost.get("Description"),
            Picture = documentPost.get("Picture"),
        )
        return postInfo
    }

    override suspend fun userLikeAPost(postId: String, userId: String) {
        val documentPost = db.collection("posts").document(postId).get()
        var countLikes : Int = documentPost.get("Likes")
        val dataLike = hashMapOf(
            "Likes" to countLikes + 1,
        )
        db.collection("posts").document(postId).update(dataLike)
    }

    override suspend fun userDislikeAPost(postId: String, userId: String) {
        val documentPost = db.collection("posts").document(postId).get()
        var countLikes : Int = documentPost.get("Likes")
        val dataLike = hashMapOf(
            "Likes" to countLikes - 1
        )
        db.collection("posts").document(postId).update(dataLike)
    }

    override suspend fun saveNewPost(Post: Post) {
        // Ajout var isPrivate
        val post = hashMapOf(
            "UserId" to Post.UserId,
            "Picture" to Post.Picture,
            "Description" to Post.Description,
            "Likes" to 0,
        )
        db.collection("posts").add(post)
    }

}
