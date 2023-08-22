package ru.mvlikhachev.domain.usecase

import com.auth0.jwt.JWTVerifier
import ru.mvlikhachev.authentification.JwtService
import ru.mvlikhachev.data.model.UserModel
import ru.mvlikhachev.domain.repository.UserRepository


class UserUseCase(
    private val repositoryImpl: UserRepository,
    private val jwtService: JwtService
) {

    suspend fun createUser(userModel: UserModel) = repositoryImpl.insertUser(userModel =  userModel)

    suspend fun findUserByEmail(email: String) = repositoryImpl.getUserByEmail(email = email)

    fun generateToken(userModel: UserModel): String = jwtService.generateToken(user = userModel)

    fun getGwtVerifier(): JWTVerifier = jwtService.getVerifier()

}