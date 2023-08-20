package ru.mvlikhachev.domain.repository

import ru.mvlikhachev.data.model.UserModel

interface UserRepository {

    suspend fun getUserByEmail(email: String): UserModel?

    suspend fun insertUser(userModel: UserModel)

}