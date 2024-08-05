package uz.khusinov.karvon.domain.model

data class ConfirmResponse(
    val name: Any,
    val phone: Int,
    val role: Int,
    val token: String,
    val user_id: Int
)