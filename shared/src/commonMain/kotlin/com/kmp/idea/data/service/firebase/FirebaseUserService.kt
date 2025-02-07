package com.kmp.idea.data.service.firebase

import com.kmp.idea.domain.model.User
import com.kmp.idea.domain.repository.IFirebaseUserService
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore

class FirebaseUserService: IFirebaseUserService {
    private val auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore

    override suspend fun getUserDataById(id: String): User {
        val listUsers = db.collection("user").get()
        var userInfo = User("","","")

        if (listUsers != null){
            listUsers.documents.forEach {
                if( it.id == id){
                    val documentUser = db.collection("user").document(it.id).get()
                    userInfo = User(
                        UserId = it.id,
                        Email = documentUser.get("Email"),
                        ProfilPicture = documentUser.get("ProfilPicture"),
                    )
                }
            }
        }
        return userInfo
    }

    override suspend fun getUserCurrentUID(): String {
        return auth.currentUser?.uid ?: ""
    }

    // Get User Account State Private or Public

    override suspend fun modifyUsername(username:String){
        val dataLike = hashMapOf(
            "Username" to username
        )
        if (auth.currentUser?.uid != "") {
            auth.currentUser?.uid?.let { db.collection("user").document(it).update(dataLike) }
        }
    }

    override suspend fun modifyEmail(email: String) {
        if(auth.currentUser != null){
            auth.currentUser!!.updateEmail(email)
        }
    }

    override suspend fun modifyPassword(newPassword: String) {
        if(auth.currentUser != null){
            auth.currentUser!!.updatePassword(newPassword)
        }
    }

    override suspend fun userSignOut() {
        auth.signOut()
    }
}