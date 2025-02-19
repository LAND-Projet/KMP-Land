package com.kmp.idea.data.dao


import com.kmp.idea.data.local.dao.IUserDAO
import com.kmp.idea.data.local.mapper.toUser
import com.kmp.idea.database.IdeaDB
import com.kmp.idea.domain.model.User

class UserDAO(db: IdeaDB):IUserDAO {
    private val queries = db.landQueries

    override suspend fun getUserById(userId: String): User? {
        return queries.getUserById(userId)
            .executeAsOneOrNull()
            ?.toLandUser()
    }

    override suspend fun insertUser(user: User) {
        queries.insertUser(
            UserGuid = user.UserId,
            Email = user.Email,
            Picture = user.ProfilPicture,
        )
    }

    override suspend fun deleteUserById(userId:String) {
        queries.deleteUserById(userId)
    }
}