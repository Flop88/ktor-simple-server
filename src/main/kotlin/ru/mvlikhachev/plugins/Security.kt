package ru.mvlikhachev.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import kotlinx.coroutines.runBlocking
import ru.mvlikhachev.authentification.JwtService
import ru.mvlikhachev.data.model.RoleModel
import ru.mvlikhachev.data.model.UserModel
import ru.mvlikhachev.data.repository.UserRepositoryImpl
import ru.mvlikhachev.domain.usecase.UserUseCase

fun Application.configureSecurity() {

    val jwtService = JwtService()
    val repository = UserRepositoryImpl()
    val userUseCase = UserUseCase(repository, jwtService)

    runBlocking {
        userUseCase.createUser(
            UserModel(
                id = 1,
                email = "test@test.com",
                login = "Login",
                password = "Password",
                firstName = "Den",
                lastName = "Brown",
                isActive = true,
                role = RoleModel.MODERATOR
            )
        )
    }


    authentication {
        jwt("jwt") {
            verifier(jwtService.getVerifier())
            realm = "Service server"
            validate {
                val payload = it.payload
                val email = payload.getClaim("email").asString()
                val user = userUseCase.findUserByEmail(email = email)
                user
            }
        }
    }
}
