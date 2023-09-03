package ru.mvlikhachev.utils

class Constants {

    object Role {
        const val ADMIN = "admin"
        const val MODERATOR = "moderator"
        const val CLIENT = "client"
    }

    object Error {
        const val GENERAL = "Oh, something went wrong!"
        const val WRONG_EMAIL = "Wrong email address!"
        const val INCORRECT_PASSWORD = "Incorrect password"
        const val MISSING_FIELDS = "Missing some fields!"
        const val USER_NOT_FOUND = "Opps, user not found"
    }

    object Success {
        const val CARD_ADDED_SUCCESSFULLY = "Card Added Successfully!"
        const val CARD_UPDATE_SUCCESSFULLY = "Card Updated Successfully!"
        const val CARD_DELETED_SUCCESSFULLY = "Card Deleted Successfully!"
    }

    object Value {
        const val ID = "id"
    }

}