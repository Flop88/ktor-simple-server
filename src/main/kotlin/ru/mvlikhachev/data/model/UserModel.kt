package ru.mvlikhachev.data.model

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    val email: String,
    val login: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val isActive: Boolean = false,
    val role: RoleModel,
): Principal
