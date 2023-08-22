package ru.mvlikhachev.data.model

data class CardModel(
    val id: Int,
    val owner: Int,
    val cardTitle: String,
    val cardDescription: String,
    val cardDate: String,
    val isVerified: Boolean = false
)
