package com.kmp.idea.data.local.mapper

import com.kmp.idea.domain.model.User

fun UserEntity.toUser(): User {
    return User(
        UserId = UserGuid,
        Email = UserName,
        ProfilPicture = Picture
    )
}