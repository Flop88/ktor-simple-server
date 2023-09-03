package ru.mvlikhachev.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.mvlikhachev.domain.usecase.CardUseCase
import ru.mvlikhachev.domain.usecase.UserUseCase
import ru.mvlikhachev.routes.CardsRoute
import ru.mvlikhachev.routes.UserRoute

fun Application.configureRouting(userUseCase: UserUseCase, cardUseCase: CardUseCase) {

    routing {
        UserRoute(userUseCase = userUseCase)
        CardsRoute(cardUseCase)
    }
}
