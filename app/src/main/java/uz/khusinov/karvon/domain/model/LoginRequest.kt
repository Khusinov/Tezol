package uz.khusinov.karvon.domain.model

data class LoginRequest(
    val phone: String,
    val password: String,
)