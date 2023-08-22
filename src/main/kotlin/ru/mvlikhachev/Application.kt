package ru.mvlikhachev

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.mvlikhachev.authentification.JwtService
import ru.mvlikhachev.data.repository.CardRepositoryImpl
import ru.mvlikhachev.data.repository.UserRepositoryImpl
import ru.mvlikhachev.domain.usecase.CardUseCase
import ru.mvlikhachev.domain.usecase.UserUseCase
import ru.mvlikhachev.plugins.*
import ru.mvlikhachev.plugins.DatabaseFactory.initializationDatabase

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    val jwtService = JwtService()
    val userRepository = UserRepositoryImpl()
    val cardRepository = CardRepositoryImpl()
    val userUseCase = UserUseCase(userRepository, jwtService)
    val cardUseCase = CardUseCase(cardRepository)

    initializationDatabase()
    configureMonitoring()
    configureSerialization()
    configureSecurity(userUseCase)
//    configureRouting()
}
