package ru.mvlikhachev.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class AddCardRequest(
    val id: Int? = null,
    val cardTitle: String,
    val cardDescription: String,
    val cardDate: String,
    val isVerified: Boolean = false,
)
