package uz.khusinov.karvon.domain.model

data class ConfirmRequest(
    val code: String,
    val phone_number: String
)